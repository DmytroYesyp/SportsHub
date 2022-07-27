import {Component, Input, OnInit} from '@angular/core';
import { ToolbarService, LinkService, ImageService, HtmlEditorService } from '@syncfusion/ej2-angular-richtexteditor';
import {ArticleContent} from "../../dto/article/article-content";
import {FormBuilder, Validators} from '@angular/forms';
import {ArticleService} from "../../services/article.service";
import {Router} from "@angular/router";
import {LeagueService} from "../../services/league.service";
import {League} from "../../dto/league/league";
import {SportKindService} from "../../services/sport-kind.service";
import {SportKind} from "../../dto/sport/kind/sport-kind";
import {TeamService} from "../../services/team.service";
import {Team} from "../../dto/team/team";
import {ArticlePhotoUploaderComponent} from "./article-photo-uploader/article-photo-uploader.component";

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css'],
  providers: [ToolbarService, LinkService, ImageService, HtmlEditorService]
})
export class AddArticleComponent implements OnInit {

  @Input('childToMaster') URL: string;

  sportKinds: SportKind[] = [];


  leagues: League[] = [];

  teams: Team[] = [];

  alternativeText: string;

  form = this.fb.group({
    title: ['', [Validators.required]],
    description: ['', [Validators.required]],
    text: [''],
    publicationDate: [new Date(), [Validators.required]],
    alternativeText: ['', [Validators.required]],
    caption: ['', [Validators.required]],
    image: ['', [Validators.required]],
    league: [null, [Validators.required]],
    teams: [[]]
  })

  constructor(
    private fb: FormBuilder,
    private articleService: ArticleService,
    private leagueService: LeagueService,
    private sportKindService: SportKindService,
    private teamService: TeamService,
    private router: Router,
    private uploader: ArticlePhotoUploaderComponent
  ) {
  }

  childToParent(url){
    this.URL=url;
  }

  ngOnInit(): void {
    this.sportKindService.getSportKinds().subscribe(sportKinds => {
      this.sportKinds = sportKinds.filter(v => v.leagues.length);
    })
  }

  changeKindOfSport({value: {leagues}}) {
    this.leagues = leagues;
  }

  changeLeague({value:{teams}}) {
    this.teams = teams;
  }

  addArticle(): void {
    const formValue = this.form.value;

    console.log(formValue.title);
    console.log(formValue.publicationDate);
    console.log("URL: " + this.URL)

    const articleContent = new ArticleContent();
    articleContent.title = formValue.title;
    articleContent.description = formValue.description;
    articleContent.text = formValue.text;
    articleContent.publicationDate = formValue.publicationDate;
    articleContent.alternativeText = formValue.alternativeText;
    articleContent.caption = formValue.caption;
    articleContent.image = this.URL;
    articleContent.leagueId = formValue.league.id;
    articleContent.teamIds = formValue.teams.map(team => team.id);

    this.articleService.createArticle(articleContent).subscribe(createdArticle => {
      const goToCreatePromise = this.router.navigate(['news', createdArticle.id]);
      goToCreatePromise.then();
    });
  }


}
