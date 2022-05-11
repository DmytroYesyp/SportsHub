import {Component, Input, OnInit} from '@angular/core';

import {Observable} from 'rxjs';
import {finalize, tap} from 'rxjs/operators';
import {AngularFireStorage, AngularFireUploadTask} from "@angular/fire/compat/storage";
import {AngularFirestore} from "@angular/fire/compat/firestore";
import {ImageCroppedEvent} from "ngx-image-cropper";

@Component({
  selector: 'upload-task',
  templateUrl: './upload-task.component.html',
  styleUrls: ['./upload-task.component.css']
})
export class UploadTaskComponent implements OnInit {

  @Input() file: File;

  url1: string;

  task: AngularFireUploadTask;

  percentage: Observable<number>;
  snapshot: Observable<any>;
  downloadURL;

  imageChangedEvent: any = '';
  croppedImage: any = '';

  fileToUpload: File;
  fileBeforeCrop: File

  constructor(private storage: AngularFireStorage) { }

  ngOnInit() {
    this.startUpload();
  }

  startUpload() {
    console.log("ZHABA")
    console.log(this.file)
    console.log("ZHABA")
    // The storage path
    const path = `image/${Date.now()}_${this.file.name}`;

    // Reference to storage bucket
    const ref = this.storage.ref(path);

    // The main task
    this.task = this.storage.upload(path, this.file);

    // Progress monitoring
    // @ts-ignore
    this.percentage = this.task.percentageChanges();

    this.snapshot   = this.task.snapshotChanges().pipe(
      tap(console.log),
      // The file's download URL
      finalize( async() =>  {
        this.downloadURL = await ref.getDownloadURL().toPromise();

        // this.db.collection('files').add( { downloadURL: this.downloadURL, path });

        this.url1 = this.downloadURL;
        console.log(this.url1);
      }),
    );
  }


  isActive(snapshot) {
    return snapshot.state === 'running' && snapshot.bytesTransferred < snapshot.totalBytes;
  }



  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
    this.fileBeforeCrop = this.imageChangedEvent.target.files[0];
  }





  uploadFile(event){
    // const file = event.target.files[0];
    // this.upload(file)

    this.imageChangedEvent = event;
    // this.upload(this.fileToUpload)
    // this.fileToUpload = event.target.files[0];
  }

  dataURItoBlob(dataURI) {
    var byteString = atob(dataURI.split(',')[1]);
    var array = new ArrayBuffer(byteString.length);
    const byteArray = new Uint8Array(array);
    for (let i = 0; i < byteString.length; i++) {
      byteArray[i] = byteString.charCodeAt(i);
    }
    return new Blob([byteArray], {type:this.fileBeforeCrop.type});
  }


  imageCropped(event: ImageCroppedEvent){
    this.croppedImage = event.base64;
    // const Blob = event.;
    const imageBlob = this.dataURItoBlob(this.croppedImage);
    // console.log(imageBlob)
    this.fileToUpload = new File([imageBlob],this.fileBeforeCrop.name,{type: this.fileBeforeCrop.type})
    // console.log(this.fileToUpload)
    // console.log(event)
  }

  // imageLoaded(image: HTMLImageElement) {
  //   console.log("1")
  //   // show cropper
  // }
  // cropperReady() {
  //   console.log("2")
  //   // cropper ready
  // }
  // loadImageFailed() {
  //   console.log("3")
  //   // show message
  // }


  startUpload2() {

    // The storage path
    const path = `image/${Date.now()}_${this.fileToUpload.name}`;

    // Reference to storage bucket
    const ref = this.storage.ref(path);

    // The main task
    this.task = this.storage.upload(path, this.fileToUpload);

    // Progress monitoring
    // @ts-ignore
    this.percentage = this.task.percentageChanges();

    this.snapshot   = this.task.snapshotChanges().pipe(
      tap(console.log),
      // The file's download URL
      finalize( async() =>  {
        this.downloadURL = await ref.getDownloadURL().toPromise();

        this.url1 = this.downloadURL;
        console.log(this.url1);
      }),
    );
  }


}
