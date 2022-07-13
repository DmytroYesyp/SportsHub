import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})



export class ArticleComponent implements OnInit {

  path :string
  list:any = [];
  leagueList:any = [];
  leagueId:string;
  authenticated: boolean = false;
  date:string;
  constructor(private http : HttpClient,
              private auth: AuthService,
              private router: Router) {
  }


  ngOnInit(): void {
    if(this.auth.isAuthenticated()){
      this.authenticated = true
    }
    this.path = this.router.url
    let Id = this.getId()
    this.http.get('http://localhost:8080/news/' + Id)
      .subscribe(Response => {
        this.list=(<Array<any>>Response);
        console.log(this.list);
        this.leagueId = this.getLeagueId();
        this.http.get('http://localhost:8080/leagues/' + this.leagueId)
          .subscribe(Response => {
            this.leagueList=(<Array<any>>Response);
            console.log(this.leagueList);
          });
        this.date = this.getDate();
        console.log(this.date)
      });
  }

  getId(){
    let baseUrl = (window.location).href;
    return baseUrl.substring(baseUrl.lastIndexOf('/') + 1)
  }

  getLeagueId(){
    return this.list['leagueId']
  }

  getDate(){
    return this.list['publicationDate'].substring(0,10) + ' ' + this.list['publicationDate'].substring(11,19)
  }
}
