import {Component, Input, OnInit} from '@angular/core';
import {finalize, tap} from "rxjs/operators";
import {AngularFireStorage, AngularFireUploadTask} from "@angular/fire/compat/storage";
import {Observable} from "rxjs";
import {ImagePickerConf} from "ngp-image-picker";
import {AuthService} from "../../services/auth.service";



@Component({
  selector: 'app-uploader',
  templateUrl: './uploader.component.html',
  styleUrls: ['./uploader.component.css']
})
export class UploaderComponent implements OnInit {

  config: ImagePickerConf = {
    borderRadius: "0px",
    language: 'en',
    width: '300px',
    objectFit: 'contain',
    aspectRatio: 4 / 3,
    compressInitial: null,
    hideDownloadBtn: true
  }
  @Input() destination : string;
  @Input() AnyId : number;
  initialImage: string = '';
  imageSrc: any = '';
  file: File;




  url: string;

  task: AngularFireUploadTask;

  percentage: Observable<number>;
  snapshot: Observable<any>;
  downloadURL;

  constructor(private storage: AngularFireStorage,
              private auth: AuthService) {}

  ngOnInit(): void {
  }



  async startUpload(destination,AnyId) {
    const formData = new FormData();
    formData.append('file', this.file)


    switch (destination){
      case "AdminTeamUpload":{
        console.log("Test")
        this.auth.saveTeamImage(formData,AnyId)
        break;
      }
      case "UserProfileUpload":{
        this.auth.saveUserImage(formData);
        break;
      }
    }

  }

  async onImageChanged(dataUri) {
    this.imageSrc = dataUri;
    const blob = await (await fetch(this.imageSrc)).blob();
    this.file = new File([blob], "image", {type: blob.type})
    console.log(this.imageSrc)
  }

}
