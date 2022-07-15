import {Component, Input, OnInit} from '@angular/core';
import {finalize, tap} from "rxjs/operators";
import {AngularFireStorage, AngularFireUploadTask} from "@angular/fire/compat/storage";
import {Observable} from "rxjs";
import {getStorage, ref, getDownloadURL} from "firebase/storage"
import {AuthService} from "../../services/auth.service";
import {listAll} from "@angular/fire/storage";
import {AdminVideoCreateComponent} from "../../admin-video/admin-video-create/admin-video-create.component";
import {User} from "../../services/user";
import {HttpClient} from "@angular/common/http";
import {Video} from "../../services/video";

@Component({
  selector: 'app-video-uploader',
  templateUrl: './video-uploader.component.html',
  styleUrls: ['./video-uploader.component.css']
})
export class VideoUploaderComponent implements OnInit {

  initialImage: string = '';
  imageSrc: any = '';
  @Input() file: File;
  video: Video;



  downloadUrl;

  task: AngularFireUploadTask;

  percentage: Observable<number>;
  snapshot: Observable<any>;

  constructor(private storage: AngularFireStorage,
              private videoCreate: AdminVideoCreateComponent,
              private http: HttpClient) { }

  ngOnInit(): void {
    this.saveVideo()
  }

  save(): void{
    this.video.url = this.downloadUrl;
    this.video.is_visible = false;
    this.video.description = this.videoCreate.description

    this.http.post('http://localhost:8080/api/fireBaseVideo/addNewVideo', this.video)
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

    this.downloadUrl = await getDownloadURL(ref(storage, path));

    // await getDownloadURL(ref(storage, path)).then((url) =>{
    //   this.downloadUrl = url;
    //   console.log(url)
    // })

    await this.save();





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

    // listAll(videoRef)

  }

}
