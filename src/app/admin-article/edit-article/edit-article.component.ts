import {Component, OnInit} from '@angular/core';
import {ToolbarService, LinkService, ImageService, HtmlEditorService} from '@syncfusion/ej2-angular-richtexteditor';
import {FormBuilder, Validators} from "@angular/forms";
import {ArticleService} from "../../services/article.service";
import {LeagueService} from "../../services/league.service";
import {SportKindService} from "../../services/sport-kind.service";
import {TeamService} from "../../services/team.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ArticleContent} from "../../dto/article/article-content";
import {combineAll, forkJoin, Observable} from 'rxjs';
import {Article} from "../../dto/article/article";
import {Team} from "../../dto/team/team";
import {SportKind} from "../../dto/sport/kind/sport-kind";
import {League} from "../../dto/league/league";

@Component({
  selector: 'app-edit-article',
  templateUrl: './edit-article.component.html',
  styleUrls: ['./edit-article.component.css'],
  providers: [ToolbarService, LinkService, ImageService, HtmlEditorService]
})
export class EditArticleComponent implements OnInit {

  articleId = 0;

  sportKinds: SportKind[] = [];

  sportKind = new SportKind();

  leagues: League[] = [];

  teams: Team[] = [];

  form = this.fb.group({
    title: ['', [Validators.required]],
    description: ['', [Validators.required]],
    publicationDate: [new Date(), [Validators.required]],
    alternativeText: ['', [Validators.required]],
    caption: ['', [Validators.required]],
    image: ['', [Validators.required]],
    league: [null, [Validators.required]],
    teams: [[]]
  })

  constructor(private fb: FormBuilder,
              private articleService: ArticleService,
              private leagueService: LeagueService,
              private sportKindService: SportKindService,
              private teamService: TeamService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    const articleId = Number(this.route.snapshot.paramMap.get('id'));

    const calls = [
      this.articleService.getArticle(articleId),
      this.sportKindService.getSportKinds()
    ];

    forkJoin(calls).subscribe(([article, sportKinds]) => {
      const articleObj = article as Article;

      this.articleId = articleObj.id;

      const teamIds = articleObj.teamIds;

      this.sportKinds = (sportKinds as SportKind[]).filter(v => v.leagues.length);

      const {leagueId} = articleObj;
      let league;
      for (const sportKind of this.sportKinds) {
        league = sportKind.leagues.find(v => v.id === leagueId)
        if (league) {
          this.sportKind = sportKind;
          this.leagues = sportKind.leagues;

          break;
        }
      }

      this.teams = league.teams;

      const selectedTeams = league.teams.filter(v => teamIds.includes(v.id));
      console.log(this.teams);
      console.log(teamIds);
      console.log(selectedTeams);

      this.form.setValue({
        title: articleObj.title,
        description: articleObj.description,
        publicationDate: articleObj.publicationDate,
        alternativeText: articleObj.alternativeText,
        caption: articleObj.caption,
        image: articleObj.image,
        league: league,
        teams: selectedTeams
      })
    })
  }

  changeKindOfSport({value: {leagues}}) {
    this.leagues = leagues;
  }

  changeLeague({value:{teams}}) {
    this.teams = teams;

  }

  updateArticle(): void {
    const formValue = this.form.value;

    const articleContent = new ArticleContent();
    articleContent.title = formValue.title;
    articleContent.description = formValue.description;
    articleContent.publicationDate = formValue.publicationDate;
    articleContent.alternativeText = formValue.alternativeText;
    articleContent.caption = formValue.caption;
    articleContent.image = formValue.image;
    articleContent.leagueId = formValue.league.id;
    articleContent.teamIds = formValue.teams.map(team => team.id);

    this.articleService.updateArticle(this.articleId, articleContent).subscribe(() =>
      this.router.navigate(['news', this.articleId]).then())
  }
}
