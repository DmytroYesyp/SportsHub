import {Component, Injectable, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'news_card',
  templateUrl: './news_card.component.html',
})

export class NewsCardComponent implements OnInit {

  li:any = [];
  lis:any = [];
  constructor(private http : HttpClient){

  }

  ngOnInit(): void {
    const params = {isPublished: true};
    this.http.get(environment.URL + 'news', {params})
      .subscribe(Response => {
        console.log(Response)
        this.li=(<Array<any>>Response).slice(Math.max(0, (<Array<any>>Response).length - 11), (<Array<any>>Response).length - 1);
        console.log(this.li);
      });
  }}
