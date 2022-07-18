import {Component, EventEmitter, Injectable, Input, OnInit, Output} from '@angular/core';

import {AngularFireStorage, AngularFireUploadTask} from "@angular/fire/compat/storage";
import {Observable} from "rxjs";
import {finalize, tap} from "rxjs/operators";
import {getStorage, ref, getDownloadURL, uploadBytesResumable} from "firebase/storage"
import {ImagePickerConf} from "ngp-image-picker";

@Component({
  selector: 'app-article-photo-uploader',
  templateUrl: './article-photo-uploader.component.html',
  styleUrls: ['./article-photo-uploader.component.css']
})

@Injectable({
  providedIn: 'root'
})
export class ArticlePhotoUploaderComponent implements OnInit {


  initialImage: string = '';
  imageSrc: any = '';
  file: File;
  config: ImagePickerConf = {
    borderRadius: "0px",
    language: 'en',
    width: '300px',
    objectFit: 'contain',
    aspectRatio: 4 / 3,
    compressInitial: null,
    hideDownloadBtn: true
  }




  downloadUrl;
  url: string;

  // @Input('childToMaster') URL: string;



  @Output() childToParent: EventEmitter<any> = new EventEmitter<String>();

  task: AngularFireUploadTask;

  percentage: Observable<number>;
  snapshot: Observable<any>;
  downloadURL: string;

  constructor(private storage: AngularFireStorage) { }

  sendToParent(url){
    this.childToParent.emit(url);
  }

  ngOnInit(): void {
  }

  async startUpload(){
    const path = `article-image/${Date.now()}_${this.file.name}`;
    const ref = this.storage.ref(path);
    this.task = this.storage.upload(path, this.file);

    this.snapshot   = this.task.snapshotChanges().pipe(
      tap(),
      finalize( async() =>  {
        this.downloadURL = await ref.getDownloadURL().toPromise();
        this.url = this.downloadURL;
        console.log(this.url);
      }),
    );
  }

  async onImageChanged(dataUri) {
    this.imageSrc = dataUri;
    const blob = await (await fetch(this.imageSrc)).blob();
    this.file = new File([blob], "image", {type: blob.type})
    console.log(this.imageSrc)
  }



  async savePhoto() {
    // The storage path
    const path = `article-image/${Date.now()}_${this.file.name}`;
    const storage = getStorage();
    const videoRef = ref(storage, `article-image/${Date.now()}_${this.file.name}`)

    const uploadTask = uploadBytesResumable(videoRef, this.file);

    uploadTask.on('state_changed',
      (snapshot) => {
        // Observe state change events such as progress, pause, and resume
        // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
        const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
        console.log('Upload is ' + progress + '% done');
        switch (snapshot.state) {
          case 'paused':
            console.log('Upload is paused');
            break;
          case 'running':
            console.log('Upload is running');
            break;
        }
      },
      (error) => {
        // Handle unsuccessful uploads
      },
      () => {
        // Handle successful uploads on complete
        // For instance, get the download URL: https://firebasestorage.googleapis.com/...
        getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
          this.downloadUrl = downloadURL;
          this.url = this.downloadUrl;
          this.sendToParent(this.url)
          console.log('File available at', this.url);
          console.log("Done")
        });

      }
    );
  }

}
