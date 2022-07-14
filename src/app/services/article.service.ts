import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HOST_URL} from "../constants/http";
import {ArticleContent} from "../dto/article/article-content";
import {Article} from "../dto/article/article";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private http: HttpClient) {}

  public createArticle(article: ArticleContent): Observable<Article> {
    return this.http.post<Article>(`${HOST_URL}/news`, article);
  }

  public getArticle(id: number): Observable<Article> {
    return this.http.get<Article>(`${HOST_URL}/news/${id}`);
  }

  public getArticles(sportKindId: number = 0,
                     leagueId: number = 0,
                     teamIds: number[] = [],
                     isPublished: boolean | null = null): Observable<Article[]> {

    const params: any = {};

    if (sportKindId) {
      params.sportKindIds = [sportKindId];
    }

    if (leagueId) {
      params.leagueIds = [leagueId];
    }

    if (teamIds?.length) {
      params.teamIds = [teamIds];
    }

    if (typeof isPublished === 'boolean') {
      params.isPublished = isPublished;
    }

    return this.http.get<Article[]>(`${HOST_URL}/news`, {params})
  }

  public getPublishedArticles(): Observable<Article[]> {
    const params = {
      isPublished: true
    };

    return this.http.get<Article[]>(`${HOST_URL}/news`, {params})
  }

  public updateArticle(id: number, article: ArticleContent): Observable<void> {
    return this.http.put<void>(`${HOST_URL}/news/${id}`, article);
  }

  public deleteArticle(id: number): Observable<void> {
    return this.http.delete<void>(`${HOST_URL}/news/${id}`);
  }

  public updatePublicationStatus(id: number, isPublished: boolean): Observable<void> {
    const body = {
      value: isPublished
    }

    return this.http.put<void>(`${HOST_URL}/news/${id}/publication-status`, body);
  }

  public updateMainPageOrder(id: number, mainPageOrder: number | null): Observable<void> {
    const body = {
      value: mainPageOrder
    }

    return this.http.put<void>(`${HOST_URL}/news/${id}/main-page-order`, body);
  }
}
