import { Injectable } from '@angular/core';
import {Observable, ReplaySubject} from "rxjs";

@Injectable({providedIn: 'root'})
export class GoogleSigninService {
  private auth2: gapi.auth2.GoogleAuth
  private subject = new ReplaySubject<gapi.auth2.GoogleUser>(1)

  constructor() {
    gapi.load('auth2', () =>{
      this.auth2 = gapi.auth2.init({
        client_id: '524872866401-tpkkalp5ucs0pe3t0k3q1i47o7p0tsss.apps.googleusercontent.com'
      })
    })
  }

  public signin(){
    this.auth2.signIn({
      scope: 'https://www.googleapis.com/auth/gmail.readonly'
      }).then(user =>{
        this.subject.next(user)
    }).catch(() =>{
      // @ts-ignore
      this.subject.next(null);
    })
  }

  public signOut () {
    this.auth2.signOut()
      .then(()=>{
        // @ts-ignore
        this.subject.next(null);
      })
  }

  public observable() : Observable<gapi.auth2.GoogleUser>{
    return this.subject.asObservable()
  }


}
