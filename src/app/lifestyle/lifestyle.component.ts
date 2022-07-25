import { Component, OnInit } from '@angular/core';
import {mainPage} from "../services/main-page.service";
import {AuthService} from "../services/auth.service";
import {AppComponent} from "../app.component";
import {TranslateService} from "@ngx-translate/core";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Article} from "../dto/article/article";
import {League} from "../dto/league/league";
import {Team} from "../dto/team/team";
import {SportKind} from "../dto/sport/kind/sport-kind";
import {ArticleService} from "../services/article.service";
import {LeagueService} from "../services/league.service";
import {forkJoin} from "rxjs";

@Component({
  selector: 'app-lifestyle',
  templateUrl: './lifestyle.component.html',
  styleUrls: ['./lifestyle.component.css']
})
export class LifestyleComponent implements OnInit {

  email: string;
  dateLimits: any;
  firstName: any;
  lastName: any;
  isAdmin: boolean = false;
  li: Object;
  role: string;
  date:string;
  user: Object;
  league: any;
  authenticated: boolean = false;
  articles: Article[] = [];
  sportKindId = 0;
  leagueId = 0;
  teamIds = [];
  isPublished: boolean | null = null;

  leagues: League[] = [];

  teams: Team[] = [];

  sportKinds: SportKind[] = [];

  activeSportKind: SportKind | null = null;

  all: string[];

  constructor(private mainpage: mainPage,
              private auth: AuthService,
              private app: AppComponent,
              public translate: TranslateService,
              private router: Router,
              private http: HttpClient,
              private articleService: ArticleService,
              private route: ActivatedRoute,
              private service: LeagueService) {
  }

  getUserFromToken(){

    const token = localStorage.getItem('auth-token');
    if (!token)
      return console.log("error");
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    const tmp = JSON.parse(jsonPayload);


    this.mainpage.getUserByEmail(tmp.sub).subscribe(data => {
      this.user = data

      for (let [key, value] of Object.entries(this.user)) {
        if (key == "firstName")
          this.firstName = value;
        if (key == "lastName")
          this.lastName = value
      }

    });
    //console.log(this.firstName , this.lastName);
    return tmp;
  }


  search: String = "";
  foods: any;


  ngOnInit(): void {

    const sportKindId = Number(this.route.snapshot.paramMap.get('sportKindId'));
    this.sportKindId = sportKindId;

    const calls = [this.articleService.getPublishedArticles()]
    forkJoin(calls).subscribe(([articles]) => {
      this.articles = articles as Article[];
    });

    this.http.get('http://localhost:8080/news')
      .subscribe(Response => {
        this.li=(<Array<any>>Response).slice(Math.max(0, (<Array<any>>Response).length - 1), (<Array<any>>Response).length);
        this.http.get('http://localhost:8080/leagues/' + this.li[0]['leagueId'])
          .subscribe(Response => {
            this.league = Response;

          });
      });

    if(this.auth.isAuthenticated()){
      this.authenticated = true
    }else{
      this.auth.logout()
    }
    if(this.app.isExpired()){
      localStorage.removeItem('auth-token')
      this.router.navigate(['/login'])
    }

    this.role = this.app.getUserFromToken()
    console.log(this.role)
    if(this.role=="admin"){
      this.isAdmin = true;
    }
    this.http.get('http://localhost:8080/datelimits')
      .subscribe(Response => {
        this.dateLimits = <Array<any>>Response
      });
  }

  logOut(){
    this.auth.logout()
  }

  getDate(){
    return this.li[0]['publicationDate'].substring(0,10) + ' ' + this.li[0]['publicationDate'].substring(11,19)
  }

}
