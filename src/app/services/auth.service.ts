import {Injectable} from "@angular/core";
import {User} from "./user";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class AuthService{

  private token: string

  constructor(private http: HttpClient) {}

  register(user: User){
    return this.http.post('http://localhost:8080/api/users/registerUser', user)
  }

  forgot_password(param){
    return this.http.post('http://localhost:8080/forgot_password', null, {params:param})
  }

  reset_password(params){
    return this.http.post('http://localhost:8080/reset_password', null, {params:params})
  }

  parse() {
    const query = window.location.search.substring(1);
    const vars = query.split('&');
    let token = vars[0].split('=')
    return token[1];
  }




  login(params): Observable<{token:string}> {

    // let httpOptions = {
    //   headers: new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'}),
    // }
    // let reqHeader = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'});
    return this.http.post<{token: string}>('http://localhost:8080/login', null, {params: params})
      .pipe(
        tap(
          ({token}) =>{
            console.log(token)
            localStorage.setItem('auth-token', "Bearer " + token)
            this.setToken(token)
          }
        )
      )
  }

  setToken(token: string){
    this.token = token
  }

  getToken(): string{
    return this.token
  }

  isAuthenticated(): boolean{
    return !!this.token
  }

  logout() {
    this.setToken('')
    localStorage.clear()
  }

}
