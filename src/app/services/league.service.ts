import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HOST_URL} from "../constants/http";
import {LeagueContent} from "../dto/league/league-content";
import {League} from "../dto/league/league";

@Injectable({
  providedIn: 'root'
})
export class LeagueService {

  constructor(private http: HttpClient) {}

  public createLeague(league: LeagueContent):Observable<League> {
    return this.http.post<League>(`${HOST_URL}/leagues`, league);
  }

  public getLeagues():Observable<League[]> {
    return this.http.get<League[]>(`${HOST_URL}/leagues`)
  }

  public updateLeague(id: number, league: LeagueContent):Observable<void> {
    return this.http.put<void>(`${HOST_URL}/leagues/${id}`, league);
  }

  public deleteLeague(id: number):Observable<void> {
    return this.http.delete<void>(`${HOST_URL}/leagues/${id}`);
  }
}
