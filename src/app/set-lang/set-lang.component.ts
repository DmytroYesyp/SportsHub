import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-set-lang',
  templateUrl: './set-lang.component.html',
  styleUrls: ['./set-lang.component.css']
})
export class SetLangComponent implements OnInit {

  isShown: boolean = false ; // hidden by default


  toggleShow() {

    this.isShown = ! this.isShown;

  }

  constructor() { }

  ngOnInit(): void {
  }

}

