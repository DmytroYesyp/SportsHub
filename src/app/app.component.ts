import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition} from "@angular/material/snack-bar";
import {TranslateService} from "@ngx-translate/core";
import {Router} from "@angular/router";


@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>'

})
export class AppComponent implements OnInit{
  title = 'client';
  expTime;

  horizontalPosition: MatSnackBarHorizontalPosition = 'right';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  constructor(private auth: AuthService,
              private snackBar: MatSnackBar,
              public translate: TranslateService) {

    let lang = translate.getBrowserLang()
    if (lang != null) {
      translate.setDefaultLang(lang)
      translate.use(localStorage.getItem('lang')||lang);
    }
  }

  openSnackBar(message: string, action: string){
    this.snackBar.open(message, action, {horizontalPosition: this.horizontalPosition, verticalPosition: this.verticalPosition});
  }

  getUserFromToken(){
    const token = localStorage.getItem('auth-token');
    if (!token)
      return console.log("No such token");
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    const tmp = JSON.parse(jsonPayload);
    this.expTime = tmp.exp
    return tmp.roles[0];
  }

  isAdmin(){
    let role = this.getUserFromToken()
    console.log(role)
    return role == "admin";
  }

  isExpired(){
    return (Math.floor((new Date).getTime() / 1000)) >= this.expTime;
  }

  ngOnInit() {
    const potentialToken = localStorage.getItem('auth-token')
    if(potentialToken!==null){
      this.auth.setToken(potentialToken)
    }
  }
}
