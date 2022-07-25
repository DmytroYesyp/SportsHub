import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HOST_URL} from "../constants/http";
import {TeamContent} from "../dto/team/team-content";
import {Team} from "../dto/team/team";

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private http: HttpClient) {}

  public createTeam(team: TeamContent):Observable<Team> {
    return this.http.post<Team>(`${HOST_URL}teams`, team);
  }

  public getTeams():Observable<Team[]> {
    return this.http.get<Team[]>(`${HOST_URL}teams`)
  }

  public updateTeam(id: number, team: TeamContent):Observable<void> {
    return this.http.put<void>(`${HOST_URL}teams/${id}`, team);
  }

  public deleteTeame(id: number):Observable<void> {
    return this.http.delete<void>(`${HOST_URL}teams/${id}`);
  }
}
