import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-lang',
  templateUrl: './lang.component.html',
  styleUrls: ['./lang.component.css']
})
export class LangComponent implements OnInit {
  list: any;


  constructor(private http : HttpClient) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/language')
      .subscribe(Response => {
        this.list = (<Array<any>>Response);
        console.log(this.list)
        for (let i = 0; i < this.list.length; i++) {
          if (this.list[i]['hidden'] == true) {
            this.list.splice(i, 1)
          }
        }
      });
  }

  changeLang(lang){
    localStorage.setItem('lang', lang)
    window.location.reload()
  }
}
