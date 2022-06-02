import {Component, OnInit} from '@angular/core';
import {Team} from "../../../admin-team-page/admin-team-page.component";
import {HttpClient} from "@angular/common/http";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-social-share',
  templateUrl: './social-shae.component.html',
  styleUrls: ['./social-shae.component.css']
})
export class SocialShaeComponent implements OnInit {
  form: FormGroup
  show: Boolean = false

  constructor(private http: HttpClient) {
  }


  reverse(): void {
    this.show = !this.show
  }

  list: socialFollow[];

  ngOnInit(): void {
    this.http.get<socialFollow[]>(`http://localhost:8080/api/socialShare/getAllShares`).subscribe(data => {
      this.list = data
    })
    this.form = new FormGroup({
      url: new FormControl('', [Validators.required]),
      pictogram: new FormControl('', [Validators.required]),
    })

  }

}

export class socialFollow {
  url: string
  pictogram: string

}
