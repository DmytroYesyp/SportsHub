import {Component,Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../services/auth.service";
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})



export class ArticleComponent implements OnInit {

  path :string
  list:any = [];
  moreList:any = [];
  leagueList:any = [];
  leagueId:string;
  kindsOfSportId:string;
  authenticated: boolean = false;
  date:string;
  Id: any;
  views: any;
  teamIds: any;
  teamList: any = [];
  constructor(private http : HttpClient,
              private auth: AuthService,
              private router: Router) {
  }


  ngOnInit(): void {
    if(this.auth.isAuthenticated()){
      this.authenticated = true
    }
    this.path = this.router.url
    this.Id = this.getId()
    this.http.get('http://localhost:8080/news/' + this.Id)
      .subscribe(Response => {
        this.list=(<Array<any>>Response);
        console.log(this.list);
        this.leagueId = this.getLeagueId();
        this.kindsOfSportId = this.getKindsOfSportId();
        this.http.get('http://localhost:8080/leagues/' + this.leagueId)
          .subscribe(Response => {
            this.leagueList=(<Array<any>>Response);
            console.log(this.leagueList);
          });
        this.date = this.getDate();
        console.log(this.date)
        this.http.get('http://localhost:8080/news?kindsOfSportIds=' + this.kindsOfSportId)
          .subscribe(Response => {
            this.moreList = (<Array<any>>Response);
            for (let i = 0; i < this.moreList.length; i++){
              if (this.list['id'] == this.moreList[i]['id']){
                this.moreList.splice(i, 1);
              }
            }
            this.moreList=(<Array<any>>Response).slice(Math.max(0, (<Array<any>>Response).length - 6),
              (<Array<any>>Response).length);
            console.log(this.moreList);
            console.log(window.location)
          });
        this.views = this.list['views'] + 1
        this.http.put('http://localhost:8080/news/' + this.Id, {
          "title": this.list['title'],
          "description": this.list['description'],
          "publicationDate": this.list['publicationDate'],
          "image": this.list['image'],
          "leagueId": this.list['leagueId'],
          "views": this.views,
          "alternativeText": this.list['alternativeText'],
          "caption": this.list['caption'],
          "isPublished": this.list['isPublished'],
          "mainPageOrder": this.list['mainPageOrder']
        })
          .subscribe(() => {});
        this.teamIds = this.list['teamIds']
        for (let i = 0; i<this.teamIds.length; i++){
          this.http.get('http://localhost:8080/teams/' + this.teamIds[i])
            .subscribe(Response => {
              this.teamList.push(<Array<any>>Response);
            });
        }
        console.log(this.teamList);
      });
  }

  getId(){
    let baseUrl = (window.location).href;
    return baseUrl.substring(baseUrl.lastIndexOf('/') + 1)
  }

  getLeagueId(){
    return this.list['leagueId']
  }

  getKindsOfSportId(){
    return this.list['kindsOfSportIds']
  }

  getDate(){
    return this.list['publicationDate'].substring(0,10) + ' ' + this.list['publicationDate'].substring(11,19)
  }

  reloadCurrentPage(id) {
    let url = 'article/' + id;
    window.open(url);
  }
}
