import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SportKind} from "../dto/sport/kind/sport-kind";
import {HOST_URL} from "../constants/http";
import {SportKindContent} from "../dto/sport/kind/sport-kind-content";

@Injectable({
  providedIn: 'root'
})
export class SportKindService {

  constructor(private http: HttpClient) {}

  public createSportKind(sportKind: SportKindContent):Observable<SportKind> {
    return this.http.post<SportKind>(`${HOST_URL}sport-kinds`, sportKind);
  }

  public getSportKinds():Observable<SportKind[]> {
    return this.http.get<SportKind[]>(`${HOST_URL}sport-kinds`)
  }

  public updateSportKind(id: number, sportKind: SportKindContent):Observable<void> {
    return this.http.put<void>(`${HOST_URL}sport-kinds/${id}`, sportKind);
  }

  public deleteSportKind(id: number):Observable<void> {
    return this.http.delete<void>(`${HOST_URL}sport-kinds/${id}`);
  }
}
