import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {trustedHTMLFromString} from "@angular/material/icon/trusted-types";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {strict} from "assert";


@Component({
  selector: 'app-admin-team-page',
  templateUrl: './admin-team-page.component.html',
  styleUrls: ['./admin-team-page.component.css']
})


export class AdminTeamPageComponent implements OnInit, OnChanges {

  pressedId: number;
  listId: number;
  isVisible: boolean = false;
  createTeam: boolean = false;
  form: FormGroup

  TeamDestination: string = "AdminTeamUpload";

  imageActivated: boolean = false;


  cliclingOnimge(){
    this.imageActivated = true
  }


  toggleHide() {
    this.isVisible = false
    this.createTeam = false
  }

  toggleDelete(teamId: number) {
    this.http.delete(`http://localhost:8080/teams/` + teamId).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 250)
  }


  getImage(path: string): string {
    let base : string = "https://firebasestorage.googleapis.com/v0/b/sportshub-623db.appspot.com/o/image%2F";
    return base + path + "?alt=media";
  }


  createNewTeam() {
    this.createTeam = true
  }


  toggleShow(event: number, event2: number) {
    this.pressedId = event
    this.listId = event2
    this.isVisible = true
  }

  onSubmit2() {

    this.form.value.image_url = "User.png";

    this.http.post(`http://localhost:8080/teams`, this.form.value).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 500)
  }


  onSubmit() {



    this.auth.updateTeam(this.pressedId, this.form.value)

  }

  createRange(number) {
    var items: number[] = [];
    for (var i = 0; i <= number; i++) {
      items.push(i);
    }
    return items;
    return new Array(number);
  }

  list: Team[];
  filterString: string = '';


  constructor(private http: HttpClient, private auth: AuthService) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.http.get<Team[]>(`http://localhost:8080/teams`).subscribe(data => {
      this.list = data
    })

    this.form = new FormGroup({
      name: new FormControl('', [Validators.required]),
      image_url: new FormControl(''),
      coach: new FormControl('', [Validators.required]),
      state: new FormControl('', [Validators.required])
    })


  }


  ngOnInit(): void {
    this.http.get<Team[]>(`http://localhost:8080/teams`).subscribe(data => {
      this.list = data
    })

    this.form = new FormGroup({
      name: new FormControl('', [Validators.required]),
      image_url: new FormControl('' ),
      coach: new FormControl('', [Validators.required]),
      state: new FormControl('', [Validators.required])
    })

  }
}

export class Team {
  id: number
  name: string
  image_url: string
  coach: string
  state: string

}
