import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Team} from "../admin-team-page/admin-team-page.component";
import {HttpClient, HttpParams} from "@angular/common/http";

interface text {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-admin-video',
  templateUrl: './admin-video.component.html',
  styleUrls: ['./admin-video.component.css']
})


export class AdminVideoComponent implements OnInit, OnChanges {

  show : Number;
  ent : videoEntity[];
  selectedValue3: string = 'text-1';
  checker : number = -1;
  glass : boolean = false;

  filterString: string = '';

  vals: text[] = [
    {value: 'text-1', viewValue: 'All'},
    {value: 'text-2', viewValue: 'Published'},
    {value: 'text-3', viewValue: 'Unpublished'},
  ];

  checkerCurrentDesc : string;
  currentDesc : string;
  currentId : number;

  videoString : string;
  seeVideo : boolean = false;


  updateDescription(){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("id",this.currentId);
    queryParams = queryParams.append("val",this.currentDesc);
    this.checkerCurrentDesc = this.currentDesc;

    // @ts-ignore
    this.ent.find(i => i.id === this.currentId).description = this.currentDesc;//here is a problem

    this.http.patch(`http://localhost:8080/api/fireBaseVideo/updateDescription`,null,{params : queryParams}).subscribe()
  }


  videoFunc(url:string){
    this.videoString = url;
    this.seeVideo = true;
  }

  func(){
    if (this.selectedValue3 == 'text-1'){
      this.http.get<videoEntity[]>(`http://localhost:8080/api/fireBaseVideo/getVideos`).subscribe(data => {
        this.ent = data
      })
    }

    if (this.selectedValue3 == 'text-2'){
      this.http.get<videoEntity[]>(`http://localhost:8080/api/fireBaseVideo/getVideos?params=1`).subscribe(data => {
        this.ent = data
      })
    }

    if (this.selectedValue3 == 'text-3'){
      this.http.get<videoEntity[]>(`http://localhost:8080/api/fireBaseVideo/getVideos?params=0`).subscribe(data => {
        this.ent = data
      })
    }


    // this.http.get<videoEntity[]>(`http://localhost:8080/api/fireBaseVideo/getVideos`).subscribe(data => {
    //   this.ent = data
    // })
  }

  isPublished(val : boolean){

    if (val){
      return "Published";
    }
    else
      return "Unpublished"
  }

  constructor(private http: HttpClient) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    }

  ngOnInit(): void {
    this.http.get<videoEntity[]>(`http://localhost:8080/api/fireBaseVideo/getVideos`).subscribe(data => {
      this.ent = data;
    })

    console.log(this.seeVideo)

  }

  func2(num : number){
    this.checker = num;
  }

  deleteById(id:number){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("id",id);
    this.http.delete(`http://localhost:8080/api/fireBaseVideo/deleteById`,{params : queryParams}).subscribe()

  }

  setVisibility(id : number,rev : boolean){
    let queryParams = new HttpParams();
    queryParams = queryParams.append("id",id);
    queryParams = queryParams.append("val",rev);


    this.http.patch(`http://localhost:8080/api/fireBaseVideo/changeVisibility`,null,{params : queryParams}).subscribe()
  }

}
export class videoEntity{
  id : number;
  description : string;
  visible: boolean;
  url : string;
}
