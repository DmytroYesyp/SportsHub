import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-most-commented',
  templateUrl: './most-commented.component.html',
  styleUrls: ['./most-commented.component.css']
})
export class MostCommentedComponent implements OnInit {

  list: any;
  dateLimit: any;
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/datelimits/2')
      .subscribe(Response => {
        this.dateLimit = Response['datelim'];

        this.http.get('http://localhost:8080/news/mostcomm?limitDate=' + this.dateLimit)
          .subscribe(Response => {
            this.list = <Array<any>>Response;
            console.log(this.list)
          });
      });
  }
}
