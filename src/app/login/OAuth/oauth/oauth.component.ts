import {OAuthService} from "angular-oauth2-oidc";
import {authConfig} from "src/app/auth-config";
import {JwksValidationHandler} from "angular-oauth2-oidc-jwks";
import {Component} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../../../services/user";

@Component({
  selector: 'app-oauth',
  templateUrl: './oauth.component.html',
  styleUrls: ['./oauth.component.css']
})
export class OauthComponent{
  name: string;
  email: string;
  picture: string;
  user: User;

  constructor(private oauthService: OAuthService,
              private http: HttpClient) {
  this.configure();
}

  private configure() {
    this.oauthService.configure(authConfig);
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
    localStorage.setItem('user', this.user.email)
    return this.http.post('http://localhost:8080/api/users/registerUser', this.user).subscribe(
      ()=> {
        console.log('Register success')
      },
      error =>{
        console.log("Error happened")
      }
    )
  }

  submit(){
    console.log("I am here")
    this.oauthService.initLoginFlow();
    this.sendUser();
    console.log("I am here222")
  }

}
