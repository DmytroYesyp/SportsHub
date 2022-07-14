import {Component, NgModule, OnInit} from '@angular/core';
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

  constructor(private leagueService: LeagueService,
              private sportKindService: SportKindService,
              private teamService: TeamService,
              private articleService: ArticleService,
              private route: ActivatedRoute,) {
  }

  ngOnInit(): void {
    const calls = [this.articleService.getArticles(), this.sportKindService.getSportKinds()]
    forkJoin(calls).subscribe(([articles, sportKinds]) => {
      this.articles = articles as Article[];
      this.sportKinds = sportKinds as SportKind[];
    })
  }

  getOrderedArticles(): Article[] {
    return this.articles.filter(({mainPageOrder}) => mainPageOrder != null)
  }
}

