import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>'

})
export class AppComponent implements OnInit{
  title = 'client';

  constructor(private auth: AuthService, public translate: TranslateService) {
    let lang = translate.getBrowserLang()
    if (lang != null) {
      translate.setDefaultLang(lang)
      translate.use(localStorage.getItem('lang')||lang);
    }
  }

  ngOnInit() {
    const potentialToken = localStorage.getItem('auth-token')
    if(potentialToken!==null){
      this.auth.setToken(potentialToken)
    }
  }
}

