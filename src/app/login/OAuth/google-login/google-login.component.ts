import { Component, OnInit } from '@angular/core';
import {googleLoginConfig} from "../../../auth-config";
import {JwksValidationHandler} from "angular-oauth2-oidc-jwks";
import {OAuthService} from "angular-oauth2-oidc";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
// import {authConfig} from "../../../auth-config";


@Component({
  selector: 'app-google-login',
  templateUrl: './google-login.component.html',
  styleUrls: ['./google-login.component.css']
})
export class GoogleLoginComponent implements OnInit {
  email: string;
  params: any;

  constructor(private oauthService: OAuthService,
              private auth: AuthService,
              private router: Router) {
    this.configure();
  }

  private configure() {
    this.oauthService.configure(googleLoginConfig);
    this.oauthService.tokenValidationHandler = new JwksValidationHandler();
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
  }

  get isLoggedIn() {
    return !!this.oauthService.getIdToken();
  }

  get claims() {
    console.log(this.oauthService.getIdentityClaims())
    return this.oauthService.getIdentityClaims() as any;
  }


  sendUser(){

    this.params = {
      'email' : this.claims.email,
      'password' : 'supersecret'
    }
    return this.auth.login(this.params).subscribe(
      ()=> {
        console.log('Login success')
        this.router.navigate(['/main'])
      },
      error =>{
        console.log("Error happened")
        this.router.navigate(['/login'])
      }
    )
  }

  ngOnInit(): void{
    this.sendUser();
  }

  submit():void{

  }

}
