import {Component, Input, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AppComponent} from "../../../app.component";
import {Clipboard} from '@angular/cdk/clipboard';
import {FacebookLoginProvider, SocialAuthService, SocialUser} from "@abacritt/angularx-social-login";

@Component({
  selector: 'app-social-share',
  templateUrl: './social-shae.component.html',
  styleUrls: ['./social-shae.component.css']
})
export class SocialShaeComponent implements OnInit {
  twt : string = "twitter-share-button fa fa-twitter"
  faceb : string = "fb-share-button fa fa-facebook"
  form: FormGroup;
  fbUser:SocialUser;
  show: Boolean = false
  isAdmin: boolean = false;
  role: string;
  params: any;
  dat : string
  twet : string
  constructor(private clipboard: Clipboard,
              private http: HttpClient,
              private app: AppComponent,
              private socialAuthService: SocialAuthService
  ) {
  }
  prefix : string = "http://localhost:4200"
  @Input() path :string = "/main"



  openning(input : string){
    window.open(input + this.dat,'name','width=600,height=400')
  }

  delete(id: number) {
    this.http.delete(`http://localhost:8080/api/socialShare/deleteShares?Id=` + id).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 250)
  }

  copySite(pic : string) {
    if (pic == "fa fa-facebook"){
      console.log("it do work")
      this.facebookInit()
    }
    this.clipboard.copy(this.prefix+this.path)
  }



  OnSubmit() {

    this.http.post(`http://localhost:8080/api/socialShare/addShares`, this.form.value).subscribe()
    setTimeout(function () {
      window.location.reload()
    }, 250)
  }

  reverse(): void {
    this.show = !this.show
  }

  list: socialFollow[];


  func(){
    return 1
  }

  facebookInit(){
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID).then(data=>
      this.sendUser()
    )
  }

  sendUser(){
    // console.log("see har")
    // console.log(this.fbUser.id + "sdadsasad" +this.fbUser.authToken)
    // console.log("https://graph.facebook.com/{"+this.fbUser.id+"}/feed ?message=Join SportsHub!"+
    //   this.prefix + this.path+ " &access_token={"+this.fbUser.authToken + "}")
    //
    // this.http.post("https://graph.facebook.com/{"+this.fbUser.id+"}/feed?message=Join SportsHub!"+
    //   this.prefix + this.path+ "&access_token={"+this.fbUser.authToken + "}",null).subscribe()

    // "https://graph.facebook.com/{page-id}/feed
    //   ?message=Hello Fans!
    //   &access_token={page-access-token}"

  }



  ngOnInit(): void {
    this.dat = this.prefix + this.path
    this.twet ="https://twitter.com/intent/tweet?text=See%20more%20on%20SportsHub%3A%0A" + this.dat

    this.socialAuthService.authState.subscribe((user) => {
      this.fbUser = user;
    });

    this.http.get<socialFollow[]>(`http://localhost:8080/api/socialShare/getAllShares`).subscribe(data => {
      this.list = data
    })
    this.form = new FormGroup({
      url: new FormControl('', [Validators.required]),
      pictogram: new FormControl('', [Validators.required]),
    })

    this.role = this.app.getUserFromToken()
    console.log(this.role)
    if (this.role == "admin") {
      this.isAdmin = true;
    }
  }
}

export class socialFollow {
  id: number
  url: string
  pictogram: string
}
