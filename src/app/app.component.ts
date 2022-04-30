import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/auth.service";

@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>'

})
export class AppComponent implements OnInit{
  title = 'client';

  constructor(private auth: AuthService) {
  }

  ngOnInit() {
    const potentialToken = localStorage.getItem('auth-token')
    if(potentialToken!==null){
      this.auth.setToken(potentialToken)
    }
  }
}
