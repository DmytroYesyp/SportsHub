import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lang',
  templateUrl: './lang.component.html',
  styleUrls: ['./lang.component.css']
})
export class LangComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  changeLang(lang){
    localStorage.setItem('lang', lang)
    window.location.reload()
  }
}
