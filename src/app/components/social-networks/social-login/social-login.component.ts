import { Component, OnInit } from '@angular/core';
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

export class SocialLoginComponent implements OnInit {

  form: FormGroup;
  checker : Number = 0;
  shareList : socialFollow[];

  shareTwit : boolean;
  shareFace : boolean;

  constructor(private http: HttpClient,private app: AppComponent) { }
  list: socialFollow[];
  ngOnInit(): void {
    this.http.get<socialFollow[]>(`http://localhost:8080/api/socialLogIn/getAllLogIn`).subscribe(data => {
      this.list = data
    })

    this.http.get<socialFollow[]>(`http://localhost:8080/api/socialShare/getAllShares`).subscribe(data => {
      this.shareList = data
      for (var i =0 ; i< this.shareList.length;i++){
        if(this.shareList[i].pictogram == 'fb-share-button fa fa-facebook')
          this.shareFace = (this.shareList[i].url == '1')
        else
          this.shareTwit = (this.shareList[i].url == '1')
      }
    })





    // for (let i = 0; i < this.shareList.length ; i++){
    //   if (this.shareList[i].pictogram == 'fb-share-button fa fa-facebook'){
    //     let fb = document.getElementById('FacebookShared')as HTMLInputElement | null;
    //
    //   }
    // }
    // document.getElementById('FacebookShared')as HTMLInputElement | null;


    this.form = new FormGroup({
      url: new FormControl('', [Validators.required]),
      pictogram: new FormControl('', [Validators.required]),
    })
  }
  func(){}
  changeFunc(i:Number){
    this.checker = i;
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

      this.http.put('http://localhost:8080/api/socialShare/editSharesByPic',null,{params : queryParams1}).subscribe()

    }
  }


  delete(id: number) {
    this.http.delete(`http://localhost:8080/api/socialLogIn/deleteLogIn?Id=` + id).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 250)
  }

  OnSubmit() {
    this.http.post(`http://localhost:8080/api/socialLogIn/addLogIn`, this.form.value).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 250)
  }
}

