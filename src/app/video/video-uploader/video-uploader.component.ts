import {Component, Input, OnInit} from '@angular/core';
import {finalize, tap} from "rxjs/operators";
import {AngularFireStorage, AngularFireUploadTask} from "@angular/fire/compat/storage";
import {Observable} from "rxjs";
import {getStorage, ref, getDownloadURL} from "firebase/storage"
import {AuthService} from "../../services/auth.service";
import {listAll} from "@angular/fire/storage";

@Component({
  selector: 'app-video-uploader',
  templateUrl: './video-uploader.component.html',
  styleUrls: ['./video-uploader.component.css']
})
export class VideoUploaderComponent implements OnInit {

  initialImage: string = '';
  imageSrc: any = '';
  @Input() file: File;




  url: string;

  task: AngularFireUploadTask;

  percentage: Observable<number>;
  snapshot: Observable<any>;
  downloadURL;

  constructor(private storage: AngularFireStorage) { }

  ngOnInit(): void {
    this.saveVideo()
  }

  async saveVideo(){
    // The storage path
    const path = `video/${Date.now()}_${this.file.name}`;
    const storage = getStorage();
    const videoRef = ref(storage, 'video')
    // Reference to storage bucket
    // const ref = this.storage.ref(path);

    // The main task
    await this.storage.upload(path, this.file);

    getDownloadURL(ref(storage, path)).then((url) =>{
      this.url = url;
      console.log(url)
    })
    // Progress monitoring
    // @ts-ignore
    // this.percentage = this.task.percentageChanges();
    //
    // this.snapshot   = this.task.snapshotChanges().pipe(
    //   tap(console.log),
    //   // The file's download URL
    //   finalize( async() =>  {
    //     this.downloadURL = await ref.getDownloadURL().toPromise();
    //
    //     this.url = this.downloadURL;
    //     console.log(this.url);
    //   }),
    // );

    listAll(videoRef)

  }

}
