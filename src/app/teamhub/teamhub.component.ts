import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {mainPage} from "../services/main-page.service";
import {concatMap, from, Observable, take} from "rxjs";
import {MatDialog} from "@angular/material/dialog";
import {PopupDeleteFollowComponent} from "../pop-ups/popup-delete-follow/popup-delete-follow.component";
import {GeolocationService} from "@ng-web-apis/geolocation";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-teamhub',
  templateUrl: './teamhub.component.html',
  styleUrls: ['./teamhub.component.css']
})

export class TeamhubComponent implements OnInit {
  id: any;
  user: Object;
  userList: any;
  role: any;
  news: any =[];
  news2: any =[];
  teams: any =[];
  coords: any;
  lat: any;
  lng: any;
  Http = new XMLHttpRequest();
  country: any;
  teams2: any = [];
  teams3: any = [];
  constructor(private http : HttpClient,
              private mainpage: mainPage,
              private dialogRef: MatDialog,
              private readonly geolocation$: GeolocationService) { }

  ngOnInit(): void {
    this.getUserFromToken()
      this.http.get(environment.URL + 'follows?userId=0')
        .subscribe(() => {
          console.log(this.id)
          this.http.get(environment.URL + 'follows?userId=' + this.id)
            .subscribe((Response) => {
              this.userList = Response
              console.log(this.userList)
              from(this.userList).pipe(
                concatMap(x=> {
                  return this.getTeams(x);
                })).subscribe(Response => {
                this.teams.push(<Array<any>>Response);
              });
              console.log(this.teams)


              from(this.userList).pipe(
                concatMap(x=> {
                  return this.getNews(x);
                })).subscribe(Response => {
                this.news.push(<Array<any>>Response);
              });
              console.log(this.news)

            });
        })

      this.geolocation$.pipe(take(1)).subscribe(position => {
        this.coords = position
        this.lat = this.coords['coords']['latitude']
        this.lng = this.coords['coords']['longitude']
        this.http.get('https://api.bigdatacloud.net/data/reverse-geocode-client?latitude='+ this.lat+
          '&longitude='+this.lng+'&localityLanguage=en',
          {headers: {
              "Access-Control-Allow-Origin" : "*",
              "Access-Control-Allow-Credentials": "true",
              "Access-Control-Allow-Methods" : "GET,POST,PUT,DELETE,OPTIONS",
              "Access-Control-Allow-Headers": "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, " +
                "Access-Control-Request-Method, Access-Control-Request-Headers"
            }
          })
          .subscribe(data => {
            this.country = data['countryName']
            this.http.get(environment.URL + 'teams')
              .subscribe(responseData =>{
                this.teams2 = responseData
                for (let i = 0; i<this.teams2.length; i++){
                  if(this.teams2[i]['state']==this.country)
                    this.teams3.push(this.teams2[i])
                }
                from(this.teams3).pipe(
                  concatMap(x=> {
                    return this.getNews2(x);
                  })).subscribe(Response => {
                  this.news2.push(<Array<any>>Response);
                });
              })
          })
      });



  }



  getImage(path: string): string {
    let base : string = "https://firebasestorage.googleapis.com/v0/b/sportshub-623db.appspot.com/o/image%2F";
    return base + path + "?alt=media";
  }

  getUserFromToken() {
    const token = localStorage.getItem('auth-token')
    if (!token)
      return console.log("error");
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    const tmp = JSON.parse(jsonPayload);
    this.mainpage.getUserByEmail(tmp.sub).subscribe(data => {
      this.user = data
      for (let [key, value] of Object.entries(this.user)) {
        if (key == "id")
          this.id = value;
      }
    });
    return this.id
  }

  private getNews(x): Observable<any>{
    return this.http.get(environment.URL + 'news?limit=3&teamIds=' + x['teamId'])
  }
  private getNews2(x): Observable<any>{
    return this.http.get(environment.URL + 'news?limit=3&teamIds=' + x['id'])
  }
  private getTeams(x): Observable<any>{
    return this.http.get(environment.URL + 'teams/' + x['teamId'])
  }

  openDialog(teamId, userId, name){
    this.dialogRef.open(PopupDeleteFollowComponent, {
      data: {
        teamId: teamId,
        userId: userId,
        name: name
      }
    })
  }
}
