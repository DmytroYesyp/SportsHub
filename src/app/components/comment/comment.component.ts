import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {mainPage} from "../../services/main-page.service";
import {MatDialog} from "@angular/material/dialog";
import {PopupDeleteCommComponent} from "../../pop-ups/popup-delete-comm/popup-delete-comm.component";
import {PopupLoginCommComponent} from "../../pop-ups/popup-login-comm/popup-login-comm.component";
import {AppComponent} from "../../app.component";
import {concatMap, from, Observable} from "rxjs";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})

export class CommentComponent implements OnInit {
  count: any;
  list: any;
  listOld: any;
  listNew: any;
  val: string;
  newsId: any;
  id: any;
  user: Object;
  user2: any = [];
  role: any;
  isAdmin: boolean = false;
  sort: any;
  photoUrl: any;
  likes: any = [];
  isLiked: any = [];
  dislikes: any = [];
  isDisliked: any = [];
  commIds: any = [];
  userIds: any = [];
  mostPop: any =[];

  constructor(private http: HttpClient, private mainpage: mainPage, private dialogRef: MatDialog, private app: AppComponent) { }

  ngOnInit(): void {
    this.getUserFromToken()
    this.newsId = this.getId()
    this.http.get('http://localhost:8080/comments/news' + this.newsId)
      .subscribe(Response => {
        this.listOld = <Array<any>>Response
        this.listNew = this.reverseArr(this.listOld);
        this.list = this.listNew
        this.sortBy()
        this.count = this.list.length
        for (let i = 0; i < this.count; i++){
          this.commIds.push(this.list[i]['id'])
          this.userIds.push(this.list[i]['userId'])
        }

        from(this.userIds).pipe(
          concatMap(x=> {
              return this.getInfoUsers(x);
            })).subscribe(Response => {
          this.user2.push(<Array<any>>Response);
        });

        from(this.commIds).pipe(
          concatMap(x=> {
            return this.getInfo(x);
            })).subscribe(Response => {
          this.likes.push(<Array<any>>Response['count']);
        })

        from(this.commIds).pipe(
          concatMap(x=> {
            return this.getInfo2(x);
            })).subscribe(Response => {
          if (Response['count']==0)
            this.isLiked.push(false);
          else
            this.isLiked.push(true);
        });

        from(this.commIds).pipe(
          concatMap(x=> {
            return this.getInfo3(x);
          })).subscribe(Response => {
          this.dislikes.push(<Array<any>>Response['count']);
        })

        from(this.commIds).pipe(
          concatMap(x=> {
            return this.getInfo4(x);
          })).subscribe(Response => {
          if (Response['count']==0)
            this.isDisliked.push(false);
          else
            this.isDisliked.push(true);
        });

        this.role = this.app.getUserFromToken()
        if(this.role=="admin"){
          this.isAdmin = true;
        }
        this.photoUrl = 'assets/img/User.png'
        this.http.get('http://localhost:8080/api/pictures/getUserProfileImage',{responseType : 'text'})
          .subscribe(responseData =>{this.photoUrl = responseData})
      });
  }

  post() {
    this.val = (<HTMLInputElement>document.getElementById("myInput")).value;
    console.log(this.val)
    if (this.val!='') {
      this.http.post('http://localhost:8080/comments', {"text": this.val,
      "newsId": this.newsId,
      "userId": this.id,
      "publicationDate": new Date(),
      "isEdited": false})
        .subscribe(() => {
          window.location.reload()
        });
    }
  }

  delete(id) {
    this.http.delete('http://localhost:8080/comments/' + id)
      .subscribe(() => {
        window.location.reload()
      });
  }

  getId(){
    let baseUrl = (window.location).href;
    return baseUrl.substring(baseUrl.lastIndexOf('/') + 1)
  }

  getUserFromToken() {
    const token = localStorage.getItem('auth-token')
    if (!token)
      return console.log("error");
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    const tmp = JSON.parse(jsonPayload);
    this.mainpage.getUserByEmail(tmp.sub).subscribe(data => {
      this.user = data
      for (let [key, value] of Object.entries(this.user)) {
        if (key == "id")
          this.id = value;
      }
    });
    return tmp;
  }

  openDialog(id){
    this.dialogRef.open(PopupDeleteCommComponent, {
      data: {
        id: id,
      }
    })
  }

  getSrc(url: any) {
    return 'https://firebasestorage.googleapis.com/v0/b/sportshub-623db.appspot.com/o/image%2F' + url + '?alt=media'
  }

  getDate(i){
    return this.list[i]['publicationDate'].substring(0,10) + ' ' + this.list[i]['publicationDate'].substring(11,16)
  }

  showEdit(id, id2) {
    let x = document.getElementById(id);
    let x2 = document.getElementById(id2);
    if (x!=null) {
      if (x.style.display === "block") {
        x.style.display = "none";
      } else {
        x.style.display = "block";
      }
    }
    if (x2!=null) {
      if (x2.style.display === "block") {
        x2.style.display = "none";
      }
    }
  }

  editComment(id, comId) {
    this.val = (<HTMLInputElement>document.getElementById(comId+'comm')).value;
    console.log(this.val)
    if (this.val!='') {
      this.http.put('http://localhost:8080/comments/' + id, {"text": this.val,
      "edited": true})
        .subscribe(() => {
          window.location.reload()
        });
    }
  }

  addComment(commId, id) {
    this.val = (<HTMLInputElement>document.getElementById(id+'comm2')).value;
    if (this.val!='') {
      this.http.post('http://localhost:8080/comments', {"text": this.val,
        "newsId": this.newsId,
        "userId": this.id,
        "publicationDate": new Date(),
        "isEdited": false,
        "commId": commId})
        .subscribe(() => {
          window.location.reload()
        });
    }
  }


  checkLogin() {
    if (this.id == null){
      this.dialogRef.open(PopupLoginCommComponent, {
        data: {
          id: 0,
        },
        'height': '155px'
      })
    }
  }

  setSort(sortParam){
    localStorage.setItem('comm sort by', sortParam)
    window.location.reload()
  }

  // mostPopularSort(){
  //   this.mostPop = this.list
  //     for (let i = 0; i < this.likes.length; i++) {
  //       for (let j = 0; j < this.likes.length; j++) {
  //         if (this.likes[j] > this.likes[j + 1]) {
  //           let temp = this.mostPop[j];
  //           this.mostPop[j] = this.mostPop[j + 1];
  //           this.mostPop[j + 1] = temp;
  //         }
  //       }
  //     }
  // }

  sortBy(){
    this.sort = localStorage.getItem('comm sort by')
    if (this.sort == 'oldest first')
      this.list = this.listOld
    else if (this.sort == 'newest first')
      this.list = this.listNew
  }

  reverseArr(input) {
    let ret = new Array;
    for(var i = input.length-1; i >= 0; i--) {
      ret.push(input[i]);
    }
    return ret;
  }

  like(id, likeId){
    if (this.id != null)
        if (!this.isLiked[likeId]){
          this.http.post('http://localhost:8080/likes', {"commentId": id,
            "userId": this.id})
            .subscribe(() => {
            });
          this.likes[likeId]++
          this.isLiked[likeId] = true
          this.http.delete('http://localhost:8080/dislikes/' + id + '?userId=' + this.id)
            .subscribe(() => {
            });
          if (this.isDisliked[likeId])
            this.dislikes[likeId]--
          this.isDisliked[likeId] = false
        }
        else{
          this.http.delete('http://localhost:8080/likes/' + id + '?userId=' + this.id)
            .subscribe(() => {
            });
          this.likes[likeId]--
          this.isLiked[likeId] = false
        }
        else
          this.dialogRef.open(PopupLoginCommComponent, {
            data: {
              id: 1,
            },
            'height': '155px'
          })
  }

  dislike(id, dislikeId){
    if (this.id != null)
      if (!this.isDisliked[dislikeId]){
        this.http.post('http://localhost:8080/dislikes', {"commentId": id,
          "userId": this.id})
          .subscribe(() => {
          });
        this.dislikes[dislikeId]++
        this.isDisliked[dislikeId] = true
        this.http.delete('http://localhost:8080/likes/' + id + '?userId=' + this.id)
          .subscribe(() => {
          });
        if (this.isLiked[dislikeId])
          this.likes[dislikeId]--
        this.isLiked[dislikeId] = false
      }
      else{
        this.http.delete('http://localhost:8080/dislikes/' + id + '?userId=' + this.id)
          .subscribe(() => {
          });
        this.dislikes[dislikeId]--
        this.isDisliked[dislikeId] = false
      }
    else
      this.dialogRef.open(PopupLoginCommComponent, {
        data: {
          id: 1,
        },
        'height': '155px'
      })
  }

  private getInfo(x): Observable<any>{
    return this.http.get('http://localhost:8080/likes/count?commId=' + x);
  }
  private getInfo2(x): Observable<any>{
    return this.http.get('http://localhost:8080/likes/check?commId=' + x + '&userId=' + this.id)
  }
  private getInfo3(x): Observable<any>{
    return this.http.get('http://localhost:8080/dislikes/count?commId=' + x);
  }
  private getInfo4(x): Observable<any>{
    return this.http.get('http://localhost:8080/dislikes/check?commId=' + x + '&userId=' + this.id)
  }
  private getInfoUsers(x): Observable<any>{
    return this.http.get('http://localhost:8080/api/users/users?userId=' + x)
  }
}
