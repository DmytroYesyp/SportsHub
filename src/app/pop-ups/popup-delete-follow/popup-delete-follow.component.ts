import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-popup-delete-follow',
  templateUrl: './popup-delete-follow.component.html',
  styleUrls: ['./popup-delete-follow.component.css']
})
export class PopupDeleteFollowComponent implements OnInit {

  teamId: any;
  userId: any;
  name: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data, private http: HttpClient, private dialogRef: MatDialog) {
    this.teamId = data.teamId;
    this.userId = data.userId;
    this.name = data.name
  }

  ngOnInit(): void {
  }

  close() {
    this.dialogRef.closeAll()
  }

  delete(teamId, userId) {
    this.http.delete(environment.URL + 'follows/' + userId + '?teamId=' + teamId)
      .subscribe(() => {
        window.location.reload()
      });
  }
}
