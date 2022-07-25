import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-popup-newsletter-success',
  templateUrl: './popup-newsletter-success.component.html',
  styleUrls: ['./popup-newsletter-success.component.css']
})
export class PopupNewsletterSuccessComponent implements OnInit {
  league: any;
  leagueId: any;
  userId: any;
  unsubscribed: boolean = false;

  constructor(@Inject(MAT_DIALOG_DATA) public data,
              private http: HttpClient,
              private dialogRef: MatDialog) {
    this.league = data.league
    this.leagueId = data.leagueId
    this.userId = data.userId
  }

  ngOnInit(): void {
  }

  close(){
    this.dialogRef.closeAll()
  }

  delete(){
    this.http.delete('http://localhost:8080/newsletter/' + this.userId + '?leagueId=' + this.leagueId)
      .subscribe(() => {
        this.unsubscribed = true
      });
  }
}
