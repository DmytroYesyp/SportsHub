import { Component, OnInit } from '@angular/core';
import { ToolbarService, LinkService, ImageService, HtmlEditorService } from '@syncfusion/ej2-angular-richtexteditor';
import {ArticleContent} from "../../dto/article/article-content";
import {FormBuilder, Validators} from '@angular/forms';
import {ArticleService} from "../../services/article.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css'],
  providers: [ToolbarService, LinkService, ImageService, HtmlEditorService]
})
export class AddArticleComponent implements OnInit {

  alternativeText: string;

  form = this.fb.group({
    title: ['', [Validators.required]],
    description: ['',[Validators.required]],
    publicationDate: [new Date(), [Validators.required]],
    alternativeText: ['', [Validators.required]],
    caption: ['', [Validators.required]],
    image: ['', [Validators.required]],
    leagueId:[1,[Validators.required]],
    teamIds:[[]],
  })

  constructor(private fb: FormBuilder, private articleService: ArticleService, private router: Router ){ }

  ngOnInit(): void {
  }

  addArticle():void {
    const formValue = this.form.value;

    console.log(formValue.title);
    console.log(formValue.publicationDate);

    const articleContent = new ArticleContent();
    articleContent.title = formValue.title;
    articleContent.description = formValue.description;
    articleContent.publicationDate = formValue.publicationDate;
    articleContent.alternativeText = formValue.alternativeText;
    articleContent.caption =  formValue.caption;
    articleContent.image = formValue.image;
    articleContent.leagueId = formValue.leagueId;
    articleContent.teamIds = formValue.teamIds;

    console.log(articleContent);

    this.articleService.createArticle(articleContent).subscribe(createdArticle =>{
      const goToCreatePromise = this.router.navigate(['news', createdArticle.id]);
      goToCreatePromise.then();
    });
  }


}
