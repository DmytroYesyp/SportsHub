import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-lang',
  templateUrl: './lang.component.html',
  styleUrls: ['./lang.component.css']
})
export class LangComponent implements OnInit {
  list: any;

  constructor(private http : HttpClient) { }

  ngOnInit(): void {
    this.http.get(environment.URL + 'language')
      .subscribe(Response => {
        this.list = (<Array<any>>Response);
      });
  }

  changeLang(lang){
    localStorage.setItem('lang', lang)
    window.location.reload()
  }
}
