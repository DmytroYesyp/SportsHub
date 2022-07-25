import {Component, NgModule, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SportKind} from "../dto/sport/kind/sport-kind";
import {League} from "../dto/league/league";
import {Team} from "../dto/team/team";
import {LeagueService} from "../services/league.service";
import {SportKindService} from "../services/sport-kind.service";
import {TeamService} from "../services/team.service";
import {ArticleService} from "../services/article.service";
import {forkJoin} from "rxjs";
import {Article} from "../dto/article/article";
import {ActivatedRoute} from "@angular/router";
import {environment} from "../../environments/environment";

interface text {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css'],
})

export class AdminPageComponent implements OnInit {
  selectedValue: string;
  selectedValue2: string;
  selectedValue3: string;
  selectedValue4: string;
  dateLimit: any;
  dateLimitList: any;
  booList: any = [];

  values: text[] = [
    {value: 'text-1', viewValue: 'text-1'},
    {value: 'text-2', viewValue: 'text-2'},
    {value: 'text-3', viewValue: 'text-3'},
  ];

  sportKinds: SportKind[] = [];

  leagues: League[] = [];

  teams: Team[] = [];

  articles: Article[] = []

  // sportKindId = 0;

  isPublished = true;

//Dima
  newsletterPublished: any = []

  constructor(private http : HttpClient,
              private leagueService: LeagueService,
              private sportKindService: SportKindService,
              private teamService: TeamService,
              private articleService: ArticleService,
              private route: ActivatedRoute,) { }

  ngOnInit(): void {
    const calls = [this.articleService.getArticles(), this.sportKindService.getSportKinds()]
    forkJoin(calls).subscribe(([articles, sportKinds]) => {
      this.articles = articles as Article[];
      this.sportKinds = sportKinds as SportKind[];
    })
    this.http.get( environment.URL + 'http://localhost:8080/datelimits')
      .subscribe((Response) => {
        this.dateLimitList = <Array<any>>Response
        if (this.dateLimitList[0]['datelim']>this.dateLimitList[1]['datelim']) {
          this.dateLimit = this.dateLimitList[0]['datelim']
        }
        else {
          this.dateLimit = this.dateLimitList[1]['datelim']
        }

        this.booList[0] = this.dateLimitList[0]['datelim'] != 0;
        this.booList[1] = this.dateLimitList[1]['datelim'] != 0;
      });

    //Dima
    this.http.get('http://localhost:8080/datelimits/4')
      .subscribe((Response) => {
        this.newsletterPublished[0] = Response['datelim'] != 0
      })
    this.http.get('http://localhost:8080/datelimits/5')
      .subscribe((Response) => {
        this.newsletterPublished[1] = Response['datelim'] != 0
      })
    //----
  }

  getOrderedArticles(): Article[] {
    return this.articles.filter(({mainPageOrder}) => mainPageOrder != null)
  }


  changeDateLimit(a){
    this.http.get('http://localhost:8080/datelimits')
      .subscribe((Response) => {
        this.dateLimitList = <Array<any>>Response
        if (this.dateLimitList[0]['datelim']>this.dateLimitList[1]['datelim']) {
          this.dateLimit = this.dateLimitList[0]['datelim']
        }
        else {
          this.dateLimit = this.dateLimitList[1]['datelim']
        }

        for(let i = 0; i<3; i++) {
          if (this.dateLimitList[i]['datelim'] != 0) {
            this.http.put('http://localhost:8080/datelimits/' + (i+1), {
              "datelim": a,
            })
              .subscribe(() => {
                this.dateLimit = a
              });
          }
        }
      })

  }


  show_hide_most(id){
    this.http.get('http://localhost:8080/datelimits')
      .subscribe((Response) => {
        this.dateLimitList = <Array<any>>Response
        if (this.dateLimitList[0]['datelim']>this.dateLimitList[1]['datelim']) {
          this.dateLimit = this.dateLimitList[0]['datelim']
        }
        else {
          this.dateLimit = this.dateLimitList[1]['datelim']
        }
    if (this.booList[id-1]){
      this.http.put('http://localhost:8080/datelimits/' + id, {"datelim": 0})
        .subscribe(()=> {
          this.booList[id-1] = false
        });
    }
    else {
      this.http.put('http://localhost:8080/datelimits/' + id, {"datelim": this.dateLimitList[2]['datelim']})
        .subscribe(() => {
          this.booList[id - 1] = true
        });

    }
      })
  }

  //Dima
 newsletterPublish(id, i){
    if (this.newsletterPublished[i]){
      this.http.put('http://localhost:8080/datelimits/' + id, {"datelim": 0})
        .subscribe(() => {
          this.newsletterPublished[i] = false
        });
    }
    else{
      this.http.put('http://localhost:8080/datelimits/' + id, {"datelim": 1})
        .subscribe(() => {
          this.newsletterPublished[i] = true
        });
    }
  }
  //--

}


