import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {mainPage} from "../../services/main-page.service";

@Component({
  selector: 'app-header-user-profile',
  templateUrl: './header-user-profile.component.html',
  styleUrls: ['./header-user-profile.component.css']
})

export class HeaderUserProfileComponent implements OnInit {
  email: string;

  @Input() arr :string[] = ["Viev Profile","Change password","My surveys","My teamhub","Log out"]
  @Input() arr2 :string[]  = ["/profile","","","","/login"]

  firstName: any;
  lastName: any;
  user: Object;


  createRange(number) {
    var items: number[] = [];
    for (var i = 0; i <= number; i++) {
      items.push(i);
    }
    return items;
    return new Array(number);
  }

  constructor(private mainpage: mainPage) {
  }


  getUserFromToken() {

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
    return tmp;
  }
  ngOnInit(): void {

    this.mainpage.getUserByEmail(this.getUserFromToken())

  }
}
