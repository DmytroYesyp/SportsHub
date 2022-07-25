import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppComponent} from "../../../app.component";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {socialFollow} from "../social-share/social-shae.component";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-social-follow',
  templateUrl: './social-follow.component.html',
  styleUrls: ['./social-follow.component.css']
})
export class SocialFollowComponent implements OnInit {

  form: FormGroup
  show: Boolean = false
  isAdmin: boolean = false;
  role: string;

  constructor(private http: HttpClient,private app: AppComponent) { }
  delete(id: number) {
    this.http.delete(environment.URL + `api/socialFollow/deleteFollows?Id=` + id).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 250)
  }

  OnSubmit() {

    this.http.post(environment.URL + `api/socialFollow/addFollows`, this.form.value).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 250)
  }

  reverse(): void {
    this.show = !this.show
  }
  list: socialMore[];
  ngOnInit(): void {
    this.http.get<socialMore[]>(environment.URL + `api/socialFollow/getAllFollows`).subscribe(data => {
      this.list = data
    })

  }

}
class socialMore{
  id: number
  url: string
  pictogram: string
  isVisible :boolean
}
