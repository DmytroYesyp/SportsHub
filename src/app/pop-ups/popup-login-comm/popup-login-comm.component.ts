import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-popup-login-comm',
  templateUrl: './popup-login-comm.component.html',
  styleUrls: ['./popup-login-comm.component.css']
})
export class PopupLoginCommComponent implements OnInit {
  id: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data) {
    this.id = data.id;
  }

  ngOnInit(): void {
  }

}
