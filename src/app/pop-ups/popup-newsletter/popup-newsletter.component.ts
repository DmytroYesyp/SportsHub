import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {mainPage} from "../../services/main-page.service";
import {PopupNewsletterSuccessComponent} from "../popup-newsletter-success/popup-newsletter-success.component";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-popup-newsletter',
  templateUrl: './popup-newsletter.component.html',
  styleUrls: ['./popup-newsletter.component.css']
})
export class PopupNewsletterComponent implements OnInit {

  id: any;
  user: Object;
  email: any;
  list: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data,
              private http: HttpClient,
              private dialogRef: MatDialog,
              private mainpage: mainPage,) {
    this.email = data.email
  }

  ngOnInit(): void {
    this.getUserFromToken()
    this.http.get(environment.URL + 'leagues/')
      .subscribe(Response => {
        this.list = <Array<any>>Response
      })
  }

  post(id, name) {
    this.http.post(environment.URL + 'newsletter', {"leagueId": id,
      "userId": this.id,
      "mail": this.email
    })
      .subscribe(() => {
        this.dialogRef.closeAll()
        this.dialogRef.open(PopupNewsletterSuccessComponent, {
          data: {
            league: name,
            leagueId: id,
            userId: this.id
          }
        })
      });
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
}
