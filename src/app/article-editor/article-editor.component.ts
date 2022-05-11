import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-article-editor',
  templateUrl: './article-editor.component.html',
  styleUrls: ['./article-editor.component.css']
})
export class ArticleEditorComponent implements OnInit {
  button1: boolean = false;
  button2: boolean = false;

  @Input() url1: string;

  constructor() { }

  ngOnInit(): void {

  }

  OnSubmit(){
    alert("Hyeta")
    this.button1 = true
  }


}
