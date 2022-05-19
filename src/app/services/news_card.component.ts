import {Component, Injectable, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'news_card',
  templateUrl: './news_card.component.html',
})

export class NewsCardComponent implements OnInit {

  li:any = [];
  constructor(private http : HttpClient){

  }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/news')
      .subscribe(Response => {
        console.log(Response)
        this.li=(<Array<any>>Response).slice(Math.max(0, (<Array<any>>Response).length - 4), (<Array<any>>Response).length);
        console.log(this.li);
      });
  }}
