import {Component, OnInit} from '@angular/core';
import {mainPage} from "../services/main-page.service";
import {AuthService} from "../services/auth.service";
import {AppComponent} from "../app.component";
import {TranslateService} from "@ngx-translate/core";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {PopupDeleteFollowComponent} from "../pop-ups/popup-delete-follow/popup-delete-follow.component";
import {MatDialog} from "@angular/material/dialog";
import {PopupNewsletterComponent} from "../pop-ups/popup-newsletter/popup-newsletter.component";
import {PopupLoginCommComponent} from "../pop-ups/popup-login-comm/popup-login-comm.component";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {


  email: string;
  dateLimits: any;
  firstName: any;
  lastName: any;
  isAdmin: boolean = false;
  li: Object;
  role: string;
  date:string;
  user: Object;
  league: any;
  authenticated: boolean = false;
  newsletterPublished: any = []
  companyInfoPublished: any = []
  contributorsPublished: any = []
  lis:any = [];
  id: any;


  // news: any = []
  // globalSearchText: string = '';

  constructor(private mainpage: mainPage,
              private auth: AuthService,
              private dialogRef: MatDialog,
              private app: AppComponent,
              public translate: TranslateService,
              private router: Router,
              private http: HttpClient) {
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
        if (key == "id")
          localStorage.setItem('userId', value)
      }

    });
    return tmp;
  }


  search: String = "";
  foods: any;


  ngOnInit(): void {
    this.getUserFromToken()
    this.http.get(environment.URL + 'news')
      .subscribe(Response => {
        this.li=(<Array<any>>Response).slice(Math.max(0, (<Array<any>>Response).length - 1), (<Array<any>>Response).length);
        this.http.get(environment.URL + 'leagues/' + this.li[0]['leagueId'])
          .subscribe(Response => {
            this.league = Response;

          });
      });

    if(this.auth.isAuthenticated()){
      this.authenticated = true
    }else{
      this.auth.logout()
    }
    if(this.app.isExpired()){
      this.router.navigate(['/login'])
    }

    this.role = this.app.getUserFromToken()
    console.log(this.role)
    if(this.role=="admin"){
      this.isAdmin = true;
    }
    this.http.get(environment.URL + 'datelimits')
      .subscribe(Response => {
        this.dateLimits = <Array<any>>Response
      });

    this.http.get(environment.URL + 'datelimits/4')
      .subscribe((Response) => {
        this.newsletterPublished[0] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + 'datelimits/5')
      .subscribe((Response) => {
        this.newsletterPublished[1] = Response['datelim'] != 0
      })


    this.http.get( environment.URL +'datelimits/6')
      .subscribe((Response) => {
        this.companyInfoPublished[0] = Response['datelim'] != 0
      })
    this.http.get(environment.URL +'datelimits/7')
      .subscribe((Response) => {
        this.companyInfoPublished[1] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + 'datelimits/8')
      .subscribe((Response) => {
        this.companyInfoPublished[2] = Response['datelim'] != 0
      })
    this.http.get(environment.URL +'datelimits/9')
      .subscribe((Response) => {
        this.companyInfoPublished[3] = Response['datelim'] != 0
      })
    this.http.get(environment.URL +'datelimits/10')
      .subscribe((Response) => {
        this.companyInfoPublished[4] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + 'datelimits/11')
      .subscribe((Response) => {
        this.companyInfoPublished[5] = Response['datelim'] != 0
      })


    this.http.get(environment.URL + 'datelimits/12')
      .subscribe((Response) => {
        this.contributorsPublished[0] = Response['datelim'] != 0
      })
    this.http.get(environment.URL +'datelimits/13')
      .subscribe((Response) => {
        this.contributorsPublished[1] = Response['datelim'] != 0
      })
    this.http.get(environment.URL +'datelimits/14')
      .subscribe((Response) => {
        this.contributorsPublished[2] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + 'datelimits/15')
      .subscribe((Response) => {
        this.contributorsPublished[3] = Response['datelim'] != 0
      })
    const params = {isPublished: true};
    this.http.get(environment.URL + 'news', {params})
      .subscribe(Response => {
        console.log(Response)
        this.lis=(<Array<any>>Response).slice(Math.max(0, (<Array<any>>Response).length - 11), (<Array<any>>Response).length - 1);
        console.log(this.lis);
      });
  }

  logOut(){
    this.auth.logout()
  }

  getDate(){
    return this.li[0]['publicationDate'].substring(0,10) + ' ' + this.li[0]['publicationDate'].substring(11,19)
  }

  openDialog(){
    if (this.authenticated) {
      let val = (<HTMLInputElement>document.getElementById('email')).value
      let val2 = document.getElementById('email')
      if (val) {
        this.dialogRef.open(PopupNewsletterComponent, {
          data: {
            email: val
          }
        })
      } else {
        if (val2)
          val2.style.border = '1px solid #D72130'
      }
    }
    else{
        this.dialogRef.open(PopupLoginCommComponent, {
          data: {
            id: 2,
          },
          'height': '155px'
        })
    }
  }
}
