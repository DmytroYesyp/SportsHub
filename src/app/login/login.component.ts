import { Component, Input, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router, Params} from '@angular/router';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Subscription} from "rxjs";
import {AppComponent} from "../app.component";
import {googleLoginConfig} from "../auth-config";
import {JwksValidationHandler} from "angular-oauth2-oidc-jwks";
import {OAuthService} from "angular-oauth2-oidc";
import {FacebookLoginProvider, SocialAuthService, SocialUser} from "@abacritt/angularx-social-login";
import {environment} from "../../environments/environment";
import {socialFollow} from "../components/social-networks/social-share/social-shae.component";
import * as stream from "stream";




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy{
  params: any;
  fbUser: SocialUser;
  // @Input() formError = 'Error';
  list: socialFollow[];
  hide=true;
  // email: string
  // password: string

  facebook : string = "fa fa-facebook";
  google : string = "fa fa-google";

  // form: FormGroup;
  aSub: Subscription

  form = new FormGroup({
      email: new FormControl('', [Validators.email, Validators.required]),
      password: new FormControl('', [Validators.required, Validators.minLength(8)])
    }
  );


  OnOffFace(ent : socialFollow){
    if (ent.pictogram == this.facebook)
      return ent.url =='1';
  return false
  }
  OnOffGoogle(ent : socialFollow){
    if (ent.pictogram == this.google)
      return ent.url =='1';
    return false

  }
  constructor(private auth: AuthService,
              private router: Router,
              private route: ActivatedRoute,
              private app: AppComponent,
              private oauthService: OAuthService,
              private socialAuthService: SocialAuthService,
              private http: HttpClient
  ) {
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

  submit():void{
    this.oauthService.initLoginFlow();
    console.log("ok")
  }

  facebookInit(){
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID).then(data=>
      this.sendUser()
    )
  }

  sendUser(){
    this.params = {
      'email' : this.fbUser.email,
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

  sendUser2(){
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





  ngOnInit(): void {
    this.auth.logout()
    this.http.get<socialFollow[]>(environment.URL + `api/socialLogIn/getAllLogIn`).subscribe(data => {
      this.list = data
    })
    this.configure()
    this.socialAuthService.authState.subscribe((user) => {
      this.fbUser = user;
      console.log(this.fbUser);
    });
    this.route.queryParams.subscribe((params: Params) => {
      if (params['registered']){
        // alert("Now you registered")
        this.app.openSnackBar("Now you registered", "Ok")
      }else if(params['accessDenied']){
        // alert("You must be authorized to access this page")
        this.app.openSnackBar("You must be authorized to access this page", "Login")
      } else if(params['sessionFailed']){
        // alert("Login again")
        this.app.openSnackBar("Login again", "Ok")
      }
    })

  }

  onFormChange(){
    // this.formError = '';
  }

  // gerErrorMessage(){
  //   if(this.form.hasError('required')){
  //     return "Not valid"
  //   }
  //   return this.form.hasError('required') ? 'Not valid' : '';
  // }

  onSubmit(){
    this.form.disable()



    let params = new HttpParams({fromObject:{email:this.form.value.email, password:this.form.value.password}})

      this.aSub = this.auth.login(params).subscribe(
        () => {
          console.log('Login success')
          this.router.navigate(['/main'])
        },
        error => {
          console.warn(error)
          this.app.openSnackBar("Bad credentials, check email or password","OK")
          this.form.enable()
        }
      )
  }



  ngOnDestroy() {
    if(this.aSub){
      this.aSub.unsubscribe()
    }
  }

}
