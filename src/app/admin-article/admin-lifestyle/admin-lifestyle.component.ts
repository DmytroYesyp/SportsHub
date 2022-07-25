import { Component, OnInit } from '@angular/core';
import {Article} from "../../dto/article/article";
import {League} from "../../dto/league/league";
import {Team} from "../../dto/team/team";
import {SportKind} from "../../dto/sport/kind/sport-kind";
import {ArticleService} from "../../services/article.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LeagueService} from "../../services/league.service";
import {forkJoin} from "rxjs";

@Component({
  selector: 'app-admin-lifestyle',
  templateUrl: './admin-lifestyle.component.html',
  styleUrls: ['./admin-lifestyle.component.css']
})
export class AdminLifestyleComponent implements OnInit {

  articles: Article[] = []

  sportKindId = 0;
  leagueId = 0;
  teamIds = [];
  isPublished: boolean | null = null;

  leagues: League[] = [];

  teams: Team[] = [];

  sportKinds: SportKind[] = [];

  activeSportKind: SportKind | null = null;

  all: string[];


  constructor(private articleService: ArticleService,
              private route: ActivatedRoute,
              private router: Router,
              private service: LeagueService) {
  }


  ngOnInit(): void {
    const sportKindId = Number(this.route.snapshot.paramMap.get('sportKindId'));
    this.sportKindId = sportKindId;

    const calls = [this.articleService.getArticles(sportKindId), this.service.getLeagues()]
    forkJoin(calls).subscribe(([articles, leagues]) => {
      this.articles = articles as Article[];
      this.leagues = leagues as League[];
    })

  }

  // changeLeague({value: {id: leagueId, teams}}) {
  //   this.leagueId = leagueId;
  //   this.teams = teams;
  //
  //   this.reloadArticles();
  // }
  //
  // changeTeam({value: selectedTeams}) {
  //   const teamIds = selectedTeams.map(({id}) => id);
  //   this.teamIds = teamIds;
  //
  //
  //   this.reloadArticles();
  // }

  changePublicationStatusFilter({value}) {
    this.isPublished = JSON.parse(value);

    this.reloadArticles();
  }

  reloadArticles(): void {
    this.articleService.getArticles(this.sportKindId, this.leagueId, this.teamIds, this.isPublished)
      .subscribe((articles) => {
        this.articles = articles;
      })
  }

  removeArticle(article: Article): void {
    this.articleService.deleteArticle(article.id).subscribe(() => {
      this.articles = this.articles.filter(v => v !== article);
    });
  }

  updatePublicationStatus(article: Article): void {
    const isPublished = !article.isPublished;
    this.articleService.updatePublicationStatus(article.id, !article.isPublished).subscribe(() => {
      article.isPublished = isPublished;
    })
  }

}
