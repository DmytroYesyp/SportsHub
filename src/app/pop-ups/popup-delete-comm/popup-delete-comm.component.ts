import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-popup-delete-comm',
  templateUrl: './popup-delete-comm.component.html',
  styleUrls: ['./popup-delete-comm.component.css']
})
export class PopupDeleteCommComponent implements OnInit {
  id: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data, private http: HttpClient, private dialogRef: MatDialog) {
    this.id = data.id;
  }

  ngOnInit(): void {
  }

  delete(id) {
    this.http.delete(environment.URL + 'comments/' + id)
      .subscribe(() => {
        window.location.reload()
      });
  }

  close(){
    this.dialogRef.closeAll()
  }

}
