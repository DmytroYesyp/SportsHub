import {Component, Input, OnInit} from '@angular/core';
import {SportKind} from "../../dto/sport/kind/sport-kind";
import {League} from "../../dto/league/league";
import {Team} from "../../dto/team/team";
import {Article} from "../../dto/article/article";
import {ArticleService} from "../../services/article.service";
import {forkJoin} from "rxjs";

@Component({
  selector: 'app-admin-main-article',
  templateUrl: './admin-main-article.component.html',
  styleUrls: ['./admin-main-article.component.css']
})
export class AdminMainArticleComponent implements OnInit {

  @Input()
  sportKinds: SportKind[] = [];

  @Input()
  articles: Article[] = [];

  @Input()
  article: Article | null = null;

  @Input()
  mainPageOrder = 0;

  selectedSportKind: SportKind | null;

  leagues: League[] = [];

  selectedLeague: League | null;

  teams: Team[] = [];

  selectedTeams: Team[] = [];

  isPublished = false;


  constructor(
    private articleService: ArticleService
  ) {
  }

  ngOnInit(): void {
    this.selectedSportKind = this.sportKinds.find(sportKind =>
      this.selectedLeague = sportKind.leagues.find(league => league.id === this.article?.leagueId) ?? null) ?? null;

    this.leagues = this.selectedSportKind?.leagues ?? [];

    this.teams = this.selectedLeague?.teams ?? [];

    const teamIds = new Set(this.article?.teamIds ?? []);

    this.selectedTeams = this.teams.filter(team => teamIds.has(team.id));

    this.isPublished = this.article?.isPublished ?? false;
  }

  changeKindOfSport({value: {leagues}}) {
    this.leagues = leagues;

    this.selectedLeague = null;
    this.teams = [];
    this.selectedTeams = [];
  }

  changeLeague({value: {teams}}) {
    this.teams = teams;

    this.selectedTeams = [];
  }

  changeArticle({value: article}) {
    if (this.article) {
      const prevArticle = this.article;

      const calls = [
        this.articleService.updateMainPageOrder(prevArticle.id, null),
        this.articleService.updateMainPageOrder(article.id, this.mainPageOrder)
      ];

      forkJoin(calls).subscribe(() => {
        prevArticle.mainPageOrder = null;
        article.mainPageOrder = this.mainPageOrder;

        this.article = article;
      })
    }
  }

  changeIsPublished(): void {
    if (this.article) {
      let article = this.article;

      this.articleService.updatePublicationStatus(this.article.id, this.isPublished).subscribe(() => {
        article.isPublished = this.isPublished;
      });
    }
  }

  getFilteredArticles() {
    if (this.selectedTeams.length) {
      const teamIds = new Set(this.selectedTeams.map(team => team.id));
      return this.articles.filter(article => article.teamIds.find(teamId => teamIds.has(teamId)));
    }

    if (this.selectedLeague) {
      return this.articles.filter(article => article.leagueId === this.selectedLeague?.id);
    }

    if (this.leagues.length) {
      const leagueIds = new Set(this.leagues.map(league => league.id));
      return this.articles.filter(article => leagueIds.has(article.leagueId));
    }

    return this.articles;
  }
}
