import {Injectable} from "@angular/core";
import {User} from "./user";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from 'rxjs/operators'
import {Team} from "../admin-team-page/admin-team-page.component";

@Injectable({
  providedIn: 'root'
})
export class AuthService{

  private token: string
  user : Object

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



  updateTeam(teamId:number,team: Team){
    return this.http.put('http://localhost:8080/teams/' + teamId, team).subscribe()
  }

  saveUserImage(formData: FormData){
    return this.http.put('http://localhost:8080/api/pictures/updateUserImage', formData).subscribe()
  }

  saveTeamImage(formData: FormData, teamId:number){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("teamId",teamId);


    return this.http.put('http://localhost:8080/api/pictures/updateTeamImage', formData,{params: queryParams}).subscribe()
  }



  getUserByEmail(email: string){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("email",email);

    let token = this.getToken();
    return this.http.get('http://localhost:8080/api/users/getUserByEmail', {params: queryParams})
  }

  updateUser(body:User){

      const token = localStorage.getItem('auth-token');
      if (!token)
        return console.log("error");

      var base64Url = token.split('.')[1];
      var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      }).join(''));
      const tmp = JSON.parse(jsonPayload);


      let id ;

      this.getUserByEmail(tmp.sub).subscribe(data => {
        this.user = data

        for (let [key, value] of Object.entries(this.user)) {
          if (key == "id")
            id = value;
        }


        var PathUrl = 'http://localhost:8080/api/users/users?userId=' + id


        return this.http.put(PathUrl, body).subscribe()
      });
  }



  login(params): Observable<{token:string}> {
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
    localStorage.removeItem('auth-token')
  }

}
