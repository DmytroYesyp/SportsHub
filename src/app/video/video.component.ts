import { Component, OnInit } from '@angular/core';
import {mainPage} from "../services/main-page.service";
import {AuthService} from "../services/auth.service";
import {AppComponent} from "../app.component";
import {TranslateService} from "@ngx-translate/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpParams} from "@angular/common/http";
import {videoEntity} from "../admin-video/admin-video.component";
import {Router} from "@angular/router";

interface text {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-video',
  templateUrl: './video.component.html',
  styleUrls: ['./video.component.css']
})
export class VideoComponent implements OnInit {

  isHovering: boolean;
  path :string


  email: string;

  firstName: any;
  lastName: any;
  isAdmin: boolean = false;
  upload: boolean = false;

  role: string;

  user: Object;

  authenticated: boolean = false;

  show : Number;
  ent : videoEntity[];
  selectedValue3: string = 'text-1';
  checker : number = -1;
  glass : boolean = false;

  filterString: string = '';

  vals: text[] = [
    {value: 'text-1', viewValue: 'All'},
    {value: 'text-2', viewValue: 'Published'},
    {value: 'text-3', viewValue: 'Unpublished'},
  ];

  checkerCurrentDesc : string;
  currentDesc : string;
  currentId : number;

  videoString : string = "" ;
  seeVideo : boolean = false;


  constructor(private mainpage: mainPage,
              private auth: AuthService,
              private app: AppComponent,
              public translate: TranslateService,
              private http: HttpClient,
              private router: Router) {
  }

  toggleHover(event: boolean) {
    this.isHovering = event;
  }

  videoFunc(url:string){
    this.videoString = url;
    this.seeVideo = true;
  }

  videoBack(){
    this.videoString = "";
  }

  getUserFromToken(){

    const token = localStorage.getItem('auth-token');
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
        if (key == "firstName")
          this.firstName = value;
        if (key == "lastName")
          this.lastName = value
      }

    });
    //console.log(this.firstName , this.lastName);
    return tmp;
  }


  search: String = "";
  foods: any;


  ngOnInit(): void {
    if(this.auth.isAuthenticated()){
      this.authenticated = true
    }else{
      this.auth.logout()
    }
    this.role = this.app.getUserFromToken()
    console.log(this.role)
    if(this.role=="admin"){
      this.isAdmin = true;
    }
    this.path = this.router.url.substring(1)
    this.http.get<videoEntity[]>(`http://localhost:8080/api/fireBaseVideo/getVideos?params=1`).subscribe(data => {
      this.ent = data;
    })


    // this.mainpage.getUserByEmail(this.getUserFromToken())


    // this.firstName = user.firstName;
    // this.lastName = user.lastName;
  }

  func2(num : number){
    this.checker = num;
  }

  logOut(){
    this.auth.logout()
  }


}

