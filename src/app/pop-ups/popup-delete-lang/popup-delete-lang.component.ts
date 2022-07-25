import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-popup-delete-lang',
  templateUrl: './popup-delete-lang.component.html',
  styleUrls: ['./popup-delete-lang.component.css']
})
export class PopupDeleteLangComponent implements OnInit {
  id: any;
  lang: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data, private http: HttpClient, private dialogRef: MatDialog) {
    this.id = data.id;
    this.lang = data.name;
  }

  ngOnInit(): void {
  }

  delete(id) {
    this.http.delete(environment.URL + 'language/' + id)
      .subscribe(Response => {
        localStorage.removeItem('lang')
        window.location.reload()
      });
  }

  close(){
    this.dialogRef.closeAll()
  }
}
