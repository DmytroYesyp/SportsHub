import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {mainPage} from "../../services/main-page.service";
import {AuthService} from "../../services/auth.service";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {AppComponent} from "../../app.component";

@Component({
  selector: 'app-header-user-profile',
  templateUrl: './header-user-profile.component.html',
  styleUrls: ['./header-user-profile.component.css']
})

export class HeaderUserProfileComponent implements OnInit {

  email: string;
  profileUrl : string

  @Input() arr :string[] = ["Viev Profile","Change password","My surveys","My teamhub","Log out"]
  @Input() arr2 :string[]  = ["/profile","","","","/login"]

  firstName: any;
  lastName: any;
  role: any;
  user: Object;


  createRange(number) {
    var items: number[] = [];
    for (var i = 0; i <= number; i++) {
      items.push(i);
    }
    return items;
    return new Array(number);
  }

  constructor(private mainpage: mainPage,
              private http:HttpClient,
              private auth: AuthService,
              private router: Router,
              private app: AppComponent) {
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
        if (key == "firstName")
          this.firstName = value;
        if (key == "lastName")
          this.lastName = value
      }
    });
    return tmp;
  }


  ngOnInit(): void {
    if(this.app.isExpired()){
      localStorage.removeItem('auth-token')
      this.router.navigate(['/login'])
    }else{
      // @ts-ignore
      this.http.get('http://localhost:8080/api/pictures/getUserProfileImage',{responseType : 'text'}).subscribe(responseData =>{this.profileUrl = responseData},
        error => {
          console.warn(error)
          window.location.reload();
        })
      this.mainpage.getUserByEmail(this.getUserFromToken())
    }
  }
}
