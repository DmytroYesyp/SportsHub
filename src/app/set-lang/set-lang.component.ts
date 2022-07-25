import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatDialog} from "@angular/material/dialog";
import {PopupDeleteLangComponent} from "../pop-ups/popup-delete-lang/popup-delete-lang.component";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-set-lang',
  templateUrl: './set-lang.component.html',
  styleUrls: ['./set-lang.component.css']
})
export class SetLangComponent implements OnInit {

  isShown: boolean = false; // hidden by default
  list: any;
  booList: any = [];


  constructor(private http: HttpClient, private dialogRef: MatDialog) {
  }

  ngOnInit(): void {
    this.http.get(environment.URL + 'language')
      .subscribe(Response => {
        this.list = (<Array<any>>Response);
        this.list.forEach(el=>{
          this.booList.push(el['hidden'])
        })
        console.log(this.booList)
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

  show_hide(langId, i){
    if (this.booList[i]){
      this.http.put(environment.URL + 'language/' + langId, {"hidden": "false"})
        .subscribe(()=> {
          this.booList[i] = false
        });
    }
    else {
      this.http.put(environment.URL + 'language/' + langId, {"hidden": "true"})
        .subscribe(() => {
          localStorage.removeItem('lang')
          this.booList[i] = true
        });
    }
  }
}

