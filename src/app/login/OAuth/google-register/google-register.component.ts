import {OAuthService} from "angular-oauth2-oidc";
import {googleRegisterConfig} from "src/app/auth-config";
import {JwksValidationHandler} from "angular-oauth2-oidc-jwks";
import {Component, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../../../services/user";
// import {authConfig} from "../../../auth-config";
import {Router} from "@angular/router";

@Component({
  selector: 'app-oauth',
  templateUrl: './google-register.component.html',
  styleUrls: ['./google-register.component.css']
})
export class GoogleRegisterComponent implements OnInit{
  name: string;
  email: string;
  picture: string;
  user: User;

  constructor(private oauthService: OAuthService,
              private http: HttpClient,
              private router: Router) {
  this.configure();
}

  private configure() {
    this.oauthService.configure(googleRegisterConfig);
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

    this.user = {
      'firstName' : this.claims.given_name,
      'lastName': this.claims.family_name,
      'email' : this.claims.email,
      'logo_url' : this.claims.picture,
      'password' : 'supersecret'
    }
    return this.http.post('http://localhost:8080/api/users/registerUser', this.user).subscribe(
      ()=> {
        console.log('Register success')
        this.router.navigate(['/login'])
      },
      error =>{
        console.log("Error happened")
        this.router.navigate(['/register'])
      }
    )
  }

  ngOnInit(): void{
    this.sendUser();
  }

  submit():void{
    // this.oauthService.initLoginFlow();
    // console.log("syka")
    // this.sendUser();
    // console.log("pizda")
  }

}
