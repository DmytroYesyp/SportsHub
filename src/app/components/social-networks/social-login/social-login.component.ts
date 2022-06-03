import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {AppComponent} from "../../../app.component";
import {socialFollow} from "../social-share/social-shae.component";

@Component({
  selector: 'app-social-login',
  templateUrl: './social-login.component.html',
  styleUrls: ['./social-login.component.css']
})
export class SocialLoginComponent implements OnInit {
  form: FormGroup
  constructor(private http: HttpClient,private app: AppComponent) { }
  list: socialFollow[];
  ngOnInit(): void {
    this.http.get<socialFollow[]>(`http://localhost:8080/api/socialLogIn/getAllLogIn`).subscribe(data => {
      this.list = data
    })
    this.form = new FormGroup({
      url: new FormControl('', [Validators.required]),
      pictogram: new FormControl('', [Validators.required]),
    })
  }

  delete(id: number) {
    this.http.delete(`http://localhost:8080/api/socialLogIn/deleteLogIn?Id=` + id).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 250)
  }

  OnSubmit() {
    this.http.post(`http://localhost:8080/api/socialLogIn/addLogIn`, this.form.value).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 250)
  }
}
