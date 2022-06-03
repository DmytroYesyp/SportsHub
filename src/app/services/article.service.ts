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

  public createArticle(article:ArticleContent):Observable<Article> {
    return this.http.post<Article>(`${HOST_URL}/news`, article);
  }

  public getArticle(id:number): Observable<Article> {
    return this.http.get<Article>(`${HOST_URL}/news/${id}`);
  }

  public getArticles():Observable<Article[]> {
    return this.http.get<Article[]>(`${HOST_URL}/news`)
  }

  public updateArticle(id: number, article: ArticleContent):Observable<void> {
    return this.http.put<void>(`${HOST_URL}/news/${id}`, article);
  }

  public deleteArticle(id: number):Observable<void> {
    return this.http.delete<void>(`${HOST_URL}/news/${id}`);
  }
}
