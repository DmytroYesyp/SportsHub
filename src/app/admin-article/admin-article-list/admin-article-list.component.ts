import { Component, OnInit } from '@angular/core';
import {Article} from "../../dto/article/article";
import {ArticleService} from "../../services/article.service";
import {ActivatedRoute, Router} from "@angular/router";
import {League} from "../../dto/league/league";
import {Team} from "../../dto/team/team";
import {SportKind} from "../../dto/sport/kind/sport-kind";

@Component({
  selector: 'app-admin-article-list',
  templateUrl: './admin-article-list.component.html',
  styleUrls: ['./admin-article-list.component.css']
})
export class AdminArticleListComponent implements OnInit {

  articles: Article[] = []

  sportKindId = 0;

  leagues: League[] = [];

  teams: Team[] = [];

  sportKinds: SportKind[] = [];

  activeSportKind: SportKind | null = null;


  constructor(private articleService: ArticleService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    const sportKindId = Number(this.route.snapshot.paramMap.get('sportKindId'));
    this.articleService.getArticlesBySportKind(sportKindId).subscribe((articles) =>{
      this.articles = articles;
    })
  }

  // removeArticle(): void {
  //   this.articleService.deleteArticle(this.articles.id).subscribe(()=>
  //   this.router.navigate(['news']).then());
  // }

}
