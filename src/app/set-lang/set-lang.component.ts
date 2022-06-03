import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatDialog} from "@angular/material/dialog";
import {PopupDeleteLangComponent} from "../pop-ups/popup-delete-lang/popup-delete-lang.component";

@Component({
  selector: 'app-set-lang',
  templateUrl: './set-lang.component.html',
  styleUrls: ['./set-lang.component.css']
})
export class SetLangComponent implements OnInit {

  isShown: boolean = false; // hidden by default
  list: any;


  constructor(private http: HttpClient, private dialogRef: MatDialog) {
  }

  ngOnInit(): void {
    this.http.get('http://localhost:8080/language')
      .subscribe(Response => {
        this.list = (<Array<any>>Response);
        console.log(this.list)
      });
  }

  openDialog(id, name){
    this.dialogRef.open(PopupDeleteLangComponent, {
      data: {
        id: id,
        name: name
      }
    })
  }

  func() {
    let chbox;
    chbox = document.getElementById('chk');
    if (chbox.checked) {
      alert('Выбран');
    } else {
      alert('Не выбран');
    }
  }


  hide(id) {
    this.http.put('http://localhost:8080/language/' + id, {"hidden": "true"})
      .subscribe(Response => {
        localStorage.removeItem('lang')
        window.location.reload()
      });
  }

  show(id) {
    this.http.put('http://localhost:8080/language/' + id, {"hidden": "false"})
      .subscribe(Response => {
        window.location.reload()
      });
  }
}

