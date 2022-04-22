import {Injectable} from "@angular/core";
import {User} from "./user";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService{

  constructor(private http: HttpClient) {}

  register(user: User){
    return this.http.post('http://localhost:8080/api/users/registerUser', user)
  }


  login(user: User): Observable<{token:string}> {
    return this.http.post<{token: string}>('http://localhost:8080/login', user)
  }


}
