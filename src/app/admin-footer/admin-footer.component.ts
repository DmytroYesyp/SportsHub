import { Component, OnInit } from '@angular/core';
import {mainPage} from "../services/main-page.service";
import {AuthService} from "../services/auth.service";
import {AppComponent} from "../app.component";
import {TranslateService} from "@ngx-translate/core";
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";


@Component({
  selector: 'app-admin-footer',
  templateUrl: './admin-footer.component.html',
  styleUrls: ['./admin-footer.component.css']
})
export class AdminFooterComponent implements OnInit {

  user: Object;
  firstName: any;
  lastName: any;

  role: string;
  isAdmin: boolean = false;
  authenticated: boolean = false;

  isVisible: any = 0;
  isSelected: boolean = true;
  isVisibleBelow: any = 0;
  isSelectedBelow: boolean = true;

  Id: any;
  list: any[];

  dateLimit: any;
  dateLimitList: any;
  booList: any = [];

  newsletterPublished: any = []
  companyInfoPublished: any = []
  contributorsPublished: any = []

  val: string;

  constructor(private mainpage: mainPage, private auth: AuthService, private app: AppComponent, public translate: TranslateService, private http : HttpClient) { }

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

  form = new FormGroup({
    text: new FormControl('', [Validators.required]),
  })

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

    this.http.get(environment.URL + `datelimits`)
      .subscribe((Response) => {
        this.dateLimitList = <Array<any>>Response
        if (this.dateLimitList[0]['datelim']>this.dateLimitList[1]['datelim']) {
          this.dateLimit = this.dateLimitList[0]['datelim']
        }
        else {
          this.dateLimit = this.dateLimitList[1]['datelim']
        }

        this.booList[0] = this.dateLimitList[0]['datelim'] != 0;
        this.booList[1] = this.dateLimitList[1]['datelim'] != 0;
      });

    this.http.get(environment.URL + `datelimits`)
      .subscribe(Response => {
        this.list=(<Array<any>>Response);
        console.log(this.list)
      })

    this.http.get(environment.URL + `datelimits/4`)
      .subscribe((Response) => {
        this.newsletterPublished[0] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/5`)
      .subscribe((Response) => {
        this.newsletterPublished[1] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/6`)
      .subscribe((Response) => {
        this.companyInfoPublished[0] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/7`)
      .subscribe((Response) => {
        this.companyInfoPublished[1] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/8`)
      .subscribe((Response) => {
        this.companyInfoPublished[2] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/9`)
      .subscribe((Response) => {
        this.companyInfoPublished[3] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/10`)
      .subscribe((Response) => {
        this.companyInfoPublished[4] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/11`)
      .subscribe((Response) => {
        this.companyInfoPublished[5] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/12`)
      .subscribe((Response) => {
        this.contributorsPublished[0] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/13`)
      .subscribe((Response) => {
        this.contributorsPublished[1] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/14`)
      .subscribe((Response) => {
        this.contributorsPublished[2] = Response['datelim'] != 0
      })
    this.http.get(environment.URL + `datelimits/15`)
      .subscribe((Response) => {
        this.contributorsPublished[3] = Response['datelim'] != 0
      })

  }


  // onSubmit(id){
  //   this.form.disable()
  //
  //   this.http.put('http://localhost:8080/page-content/' + id, {"text": this.form.value})
  //     .subscribe(() => {
  //       window.location.reload()
  //     });
  // }



  // updatePageText(id){
  //   this.val = (<HTMLInputElement>document.getElementById(id)).value;
  //   console.log(this.val)
  //   if (this.val!='') {
  //     this.http.put('http://localhost:8080/page-content/' + id, {"text": this.val})
  //       .subscribe(() => {
  //         window.location.reload()
  //       });
  //   }
  // }


  newsletterPublish(id, i){
    if (this.newsletterPublished[i]){
      this.http.put(environment.URL + `datelimits` + id, {"datelim": 0})
        .subscribe(() => {
          this.newsletterPublished[i] = false
        });
    }
    else{
      this.http.put(environment.URL + `datelimits` + id, {"datelim": 1})
        .subscribe(() => {
          this.newsletterPublished[i] = true
        });
    }
  }

  companyInfoPublish(id, i){
    if (this.companyInfoPublished[i]){
      this.http.put(environment.URL + `datelimits` + id, {"datelim": 0})
        .subscribe(() => {
          this.companyInfoPublished[i] = false
        });
    }
    else{
      this.http.put(environment.URL + `datelimits` + id, {"datelim": 1})
        .subscribe(() => {
          this.companyInfoPublished[i] = true
        });
    }
  }

  contributorsPublish(id, i){
    if (this.contributorsPublished[i]){
      this.http.put(environment.URL + `datelimits` + id, {"datelim": 0})
        .subscribe(() => {
          this.contributorsPublished[i] = false
        });
    }
    else{
      this.http.put(environment.URL + `datelimits` + id, {"datelim": 1})
        .subscribe(() => {
          this.contributorsPublished[i] = true
        });
    }
  }

  logOut(){
    this.auth.logout()
  }

}
