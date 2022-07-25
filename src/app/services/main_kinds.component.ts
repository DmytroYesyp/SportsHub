import {Component, Injectable, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'main_kinds',
  templateUrl: './main_kind.component.html',
})

export class MainKindsComponent implements OnInit {

  li:any;
  lis=[];
  constructor(private http : HttpClient){

  }

  ngOnInit(): void {
    this.http.get(environment.URL + 'sport-kinds')
      .subscribe(Response => {
        console.log(Response)
        this.li=Response;


      });
  }}
