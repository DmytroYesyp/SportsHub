import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpParams} from "@angular/common/http";
import {AppComponent} from "../../../app.component";
import {socialFollow} from "../social-share/social-shae.component";
import {unwatchFile} from "fs";
import {share} from "rxjs";
import {getBoolean} from "@angular/fire/remote-config";

@Component({
  selector: 'app-social-login',
  templateUrl: './social-login.component.html',
  styleUrls: ['./social-login.component.css']
})

export class SocialLoginComponent implements OnInit , OnChanges {

  checker : Number = 0;
  shareList : socialFollow[];


  list: socialFollow[];
  listFollow: socialMore[];
  listLogIn: socialFollow[];


  shareTwit : boolean;
  shareFace : boolean;
  allShare : boolean;


  FollowFace : boolean;
  FollowTwit : boolean;
  FollowYoutube : boolean;
  allFollow : boolean;

  LogInFace : boolean;
  LogInTwit : boolean;
  LogInGoogle : boolean;
  allLogIn : boolean;

  constructor(private http: HttpClient,private app: AppComponent) { }

  ngOnInit(): void {
    this.http.get<socialFollow[]>(`http://localhost:8080/api/socialLogIn/getAllLogIn`).subscribe(data => {
      this.list = data
    })



    this.http.get<socialFollow[]>(`http://localhost:8080/api/socialShare/getAllShares`).subscribe(data => {
      this.shareList = data
      for (var i =0 ; i< this.shareList.length;i++){
        if(this.shareList[i].pictogram == 'fb-share-button fa fa-facebook')
          this.shareFace =(this.shareList[i].url == '1')
        else
          this.shareTwit = (this.shareList[i].url == '1')
      }
    })

    this.http.get<socialMore[]>(`http://localhost:8080/api/socialFollow/getAllFollows`).subscribe(data => {
      this.listFollow = data

      for (var i =0 ; i< this.listFollow.length;i++){

        switch (this.listFollow[i].pictogram){
          case 'fa fa-twitter':{
            this.FollowTwit = this.listFollow[i].isVisible
            break;
          }
          case 'fa fa-youtube':{
            this.FollowYoutube = this.listFollow[i].isVisible
            break;
          }
          case 'fa fa-facebook':{
            this.FollowFace = this.listFollow[i].isVisible
            break;
          }
        }

      }



    })


    this.http.get<socialFollow[]>(`http://localhost:8080/api/socialLogIn/getAllLogIn`).subscribe(data => {
      this.listLogIn = data


      for (var i =0 ; i< this.listLogIn.length;i++){

        switch (this.listLogIn[i].pictogram){
          case 'fa fa-twitter':{
            this.LogInTwit =(this.listLogIn[i].url == '1')
            break;
          }
          case 'fa fa-google':{
            this.LogInGoogle = (this.listLogIn[i].url == '1')
            break;
          }
          case 'fa fa-facebook':{
            this.LogInFace = (this.listLogIn[i].url == '1')
            break;
          }
        }

      }
      this.allShare = this.shareTwit || this.shareFace
      this.allFollow = this.FollowFace || this.FollowTwit || this.FollowYoutube
      this.allLogIn = this.LogInFace || this.LogInTwit  || this.LogInGoogle
    })





    // for (let i = 0; i < this.shareList.length ; i++){
    //   if (this.shareList[i].pictogram == 'fb-share-button fa fa-facebook'){
    //     let fb = document.getElementById('FacebookShared')as HTMLInputElement | null;
    //
    //   }
    // }
    // document.getElementById('FacebookShared')as HTMLInputElement | null;


    // this.form = new FormGroup({
    //   url: new FormControl('', [Validators.required]),
    //   pictogram: new FormControl('', [Validators.required]),
    // })
  }
  func(){}
  changeFunc(i:Number){
    this.checker = i;
  }

  allSecShare(){
    let queryParams = new HttpParams();
    let queryParams1 = new HttpParams();
    let TF : string
      const cb = document.getElementById('allSectionShare')as HTMLInputElement | null;
      if (cb?.checked)
        TF = '1';
      else
        TF = '0';

    queryParams = queryParams.append("pic",'twitter-share-button fa fa-twitter');
    queryParams = queryParams.append("val",TF);

    this.http.put('http://localhost:8080/api/socialShare/editSharesByPic',null,{params : queryParams}).subscribe()
    queryParams1 = queryParams1.append("pic",'fb-share-button fa fa-facebook');
    queryParams1 = queryParams1.append("val",TF);

    this.http.put('http://localhost:8080/api/socialShare/editSharesByPic',null,{params : queryParams1}).subscribe()


    this.shareTwit = TF == '1';
    this.shareFace = TF == '1';

  }
  allSecFollow(){
    let queryParams = new HttpParams();
    let TF : string
    const cb = document.getElementById('allSectionFollow')as HTMLInputElement | null;
    if (cb?.checked)
      TF = '1';
    else
      TF = '0';

    queryParams = queryParams.append("pic",'fa fa-facebook');
    queryParams = queryParams.append("isVisible",TF);
    queryParams = queryParams.append("url","");

    this.http.put('http://localhost:8080/api/socialFollow/editFollowsByPic',null,{params : queryParams}).subscribe()

    let queryParams1 = new HttpParams();
    queryParams1 = queryParams1.append("pic",'fa fa-twitter');
    queryParams1 = queryParams1.append("isVisible",TF);
    queryParams1 = queryParams1.append("url","");

    this.http.put('http://localhost:8080/api/socialFollow/editFollowsByPic',null,{params : queryParams1}).subscribe()

    let queryParams2 = new HttpParams();
    queryParams2 = queryParams2.append("pic",'fa fa-youtube');
    queryParams2 = queryParams2.append("isVisible",TF);
    queryParams2 = queryParams2.append("url","");

    this.http.put('http://localhost:8080/api/socialFollow/editFollowsByPic',null,{params : queryParams2}).subscribe()

    this.FollowFace = TF == '1';
    this.FollowTwit = TF == '1';
    this.FollowYoutube = TF == '1';

  }
  allSecLogIn(){
    let TF : string
    const cb = document.getElementById('allSectionLogIn')as HTMLInputElement | null;
    if (cb?.checked)
      TF = '1';
    else
      TF = '0';

    let queryParams = new HttpParams();
    queryParams = queryParams.append("pic",'fa fa-facebook');
    queryParams = queryParams.append("url",TF);
    this.http.put('http://localhost:8080/api/socialLogIn/editLogInByPic',null,{params : queryParams}).subscribe()

    let queryParams1 = new HttpParams();
    queryParams1 = queryParams1.append("pic",'fa fa-twitter');
    queryParams1 = queryParams1.append("url",TF);
    this.http.put('http://localhost:8080/api/socialLogIn/editLogInByPic',null,{params : queryParams1}).subscribe()

    let queryParams2 = new HttpParams();
    queryParams2 = queryParams2.append("pic",'fa fa-google');
    queryParams2 = queryParams2.append("url",TF);
    this.http.put('http://localhost:8080/api/socialLogIn/editLogInByPic',null,{params : queryParams2}).subscribe()

    this.LogInGoogle= TF == '1';
    this.LogInTwit = TF == '1';
    this.LogInFace = TF == '1';
  }
  follow(str : string){
    let queryParams = new HttpParams();
    let TF : string

    switch (str){

      case 'facebook' :{
        const cb = document.getElementById('FacebookFollow')as HTMLInputElement | null;
        if (cb?.checked)
          TF = '1';
        else
          TF = '0';

        queryParams = queryParams.append("pic",'fa fa-facebook');
        queryParams = queryParams.append("isVisible",TF);
        queryParams = queryParams.append("url","");
        this.FollowFace = TF == "1"
        break;
      }
      case 'twitter' :{
        const cb = document.getElementById('TwitterFollow')as HTMLInputElement | null;
        if (cb?.checked)
          TF = '1';
        else
          TF = '0';

        queryParams = queryParams.append("pic",'fa fa-twitter');
        queryParams = queryParams.append("isVisible",TF);
        queryParams = queryParams.append("url","");
        this.FollowTwit = TF == "1"
        break;
      }
      case 'youtube' :{
        const cb = document.getElementById('YouTubeFollow')as HTMLInputElement | null;
        if (cb?.checked)
          TF = '1';
        else
          TF = '0';

        queryParams = queryParams.append("pic",'fa fa-youtube');
        queryParams = queryParams.append("isVisible",TF);
        queryParams = queryParams.append("url","");
        this.FollowYoutube = TF == "1"
        break;
      }

    }


    this.allFollow = this.FollowTwit || this.FollowFace || this.FollowYoutube

    this.http.put('http://localhost:8080/api/socialFollow/editFollowsByPic',null,{params : queryParams}).subscribe()
  }

  shared(str : string){


    if (str == 'twitter'){
      const cb = document.getElementById('TwitterShared')as HTMLInputElement | null;
      let queryParams = new HttpParams();
      let TF : string
      if (cb?.checked)
        TF = '1';
      else
        TF = '0';

      queryParams = queryParams.append("pic",'twitter-share-button fa fa-twitter');
      queryParams = queryParams.append("val",TF);

      this.shareTwit = TF == '1'

      this.http.put('http://localhost:8080/api/socialShare/editSharesByPic',null,{params : queryParams}).subscribe()
    }
    if (str == 'facebook'){
      const cb1 = document.getElementById('FacebookShared')as HTMLInputElement | null;
      let queryParams1 = new HttpParams();
      let TF1 : string
      if (cb1?.checked)
        TF1 = '1';
      else
        TF1 = '0';
      queryParams1 = queryParams1.append("pic",'fb-share-button fa fa-facebook');
      queryParams1 = queryParams1.append("val",TF1);
      this.shareFace = TF1 == '1'
      this.http.put('http://localhost:8080/api/socialShare/editSharesByPic',null,{params : queryParams1}).subscribe()

    }

    this.allShare = this.shareTwit || this.shareFace
  }
  logIn(str : string){
    let queryParams = new HttpParams();
    let TF : string

    switch (str){

      case 'facebook' :{
        const cb = document.getElementById('FacebookLogin')as HTMLInputElement | null;
        if (cb?.checked)
          TF = '1';
        else
          TF = '0';

        queryParams = queryParams.append("pic",'fa fa-facebook');
        queryParams = queryParams.append("url",TF);

        this.LogInFace = TF == '1';

        break;
      }
      case 'twitter' :{
        const cb = document.getElementById('TwitterLogin')as HTMLInputElement | null;
        if (cb?.checked)
          TF = '1';
        else
          TF = '0';

        queryParams = queryParams.append("pic",'fa fa-twitter');
        queryParams = queryParams.append("url",TF);
        this.LogInTwit = TF == '1';
        break;
      }
      case 'google' :{
        const cb = document.getElementById('GoogleLogin')as HTMLInputElement | null;
        if (cb?.checked)
          TF = '1';
        else
          TF = '0';

        queryParams = queryParams.append("pic",'fa fa-google');
        queryParams = queryParams.append("url",TF);
        this.LogInGoogle = TF == '1';
        break;
      }

    }

    this.allLogIn = this.LogInFace || this.LogInTwit  || this.LogInGoogle
    this.http.put('http://localhost:8080/api/socialLogIn/editLogInByPic',null,{params : queryParams}).subscribe()
  }

  delete(id: number) {
    this.http.delete(`http://localhost:8080/api/socialLogIn/deleteLogIn?Id=` + id).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 250)
  }

  ngOnChanges(changes: SimpleChanges): void {


  }

  // OnSubmit() {
  //   this.http.post(`http://localhost:8080/api/socialLogIn/addLogIn`, this.form.value).subscribe()
  //   setTimeout(function () {
  //     window.location.reload()
  //   }, 250)
  // }
}
export class socialMore{
  id: number
  url: string
  pictogram: string
  isVisible: boolean
}

