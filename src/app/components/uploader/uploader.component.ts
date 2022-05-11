import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-uploader',
  templateUrl: './uploader.component.html',
  styleUrls: ['./uploader.component.css']
})
export class UploaderComponent implements OnInit {

  isHovering: boolean;


  files: File[] = [];

  toggleHover(event: boolean) {
    this.isHovering = event;
  }

  onDrop(files: FileList) {
    for (let i = 0; i < files.length; i++) {
      this.files.push(<File>files.item(i))
      console.log(files)
    }
  }

  constructor() {}

  ngOnInit(): void {
  }

}
