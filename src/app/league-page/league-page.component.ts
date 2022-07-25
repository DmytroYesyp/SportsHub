import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../services/auth.service";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-league-page',
  templateUrl: './league-page.component.html',
  styleUrls: ['./league-page.component.css']
})
export class LeaguePageComponent implements OnInit {
  list: any;
  authenticated: boolean = false;
  article: any;
  date: string;
  league: any;
  leagueId: any;

  constructor(private http : HttpClient, private auth: AuthService) { }

  ngOnInit(): void {
    if(this.auth.isAuthenticated()){
      this.authenticated = true
    }
    let Id = this.getId()
    this.http.get(environment.URL + 'news?leagueIds=' + Id)
      .subscribe(Response => {
        this.list=(<Array<any>>Response).slice(Math.max(0, (<Array<any>>Response).length - 10), (<Array<any>>Response).length);
        this.article = this.list.pop();
        console.log(this.article);
        console.log(this.list);
        this.date = this.getDate();
        console.log(this.date)
        this.leagueId = this.getLeagueId();
        this.http.get(environment.URL + 'leagues/' + this.leagueId)
          .subscribe(Response => {
            this.league=(<Array<any>>Response);
            console.log(this.league);
          });
      });

  }

  getId(){
    let baseUrl = (window.location).href;
    return baseUrl.substring(baseUrl.lastIndexOf('/') + 1)
  }

  getDate(){
    return this.article['publicationDate'].substring(0,10) + ' ' + this.article['publicationDate'].substring(11,19)
  }

  getLeagueId(){
    return this.article['leagueId']
  }
}
