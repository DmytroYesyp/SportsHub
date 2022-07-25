import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-most-popular',
  templateUrl: './most-popular.component.html',
  styleUrls: ['./most-popular.component.css']
})
export class MostPopularComponent implements OnInit {

  list: any;
  dateLimit: any;
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get(environment.URL + 'datelimits/1')
      .subscribe(Response => {
        this.dateLimit = Response['datelim'];

        this.http.get(environment.URL + 'news/mostpop?limitDate=' + this.dateLimit)
          .subscribe(Response => {
            this.list = <Array<any>>Response;
            console.log(this.list)
          });
      });

  }
}
