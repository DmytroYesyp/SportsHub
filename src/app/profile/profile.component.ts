import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {HttpClient, HttpParams} from "@angular/common/http";
import {AppComponent} from "../app.component";
import {mainPage} from "../services/main-page.service";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  isVisible: any = 0;
  isSelected: boolean = true;
  form: FormGroup
  hide = true;
  form2: FormGroup
  form3: FormGroup
  imageAdd: boolean = false;
  profileUrl: string;
  UserProfileUpload : string = 'UserProfileUpload';
  teams: any = []
  followedTeams: any = []
  followedTeams2: any = []


  arr :string[] = ["My surveys","My teamhub","Log out"]
  arr2 :string[]  = ["","/team_hub", "/register"]
  email: string;
  user: Object;


  searchText: string = '';
  userId: any;


  constructor(private auth:AuthService,
              private http:HttpClient,
              private app: AppComponent,
              private mainpage: mainPage) {
  }

  getUserFromToken() {
    const token = localStorage.getItem('auth-token')
    if (!token)
      return console.log("error");
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    const tmp = JSON.parse(jsonPayload);
    this.mainpage.getUserByEmail(tmp.sub).subscribe(data => {
      this.user = data
      for (let [key, value] of Object.entries(this.user)) {
        if (key == "id")
          this.userId = value;
      }
    });
    return this.userId
  }

  Submit(){
    this.auth.updateUser(this.form.value)
    setTimeout(function (){window.location.reload()},500)
  }


  getImage(path: string): string {
    let base : string = "https://firebasestorage.googleapis.com/v0/b/sportshub-623db.appspot.com/o/image%2F";
    return base + path + "?alt=media";
  }

  Submit2(){

    let queryParams = new HttpParams();
    queryParams = queryParams.append("OldPassword",this.form2.value.OldPassword);
    queryParams = queryParams.append("NewPassword",this.form2.value.NewPassword);
    queryParams = queryParams.append("passwordConfirmation",this.form2.value.passwordConfirmation);

    // let url : string = "http://localhost:8080/api/users/checkPass?OldPassword=12345678&NewPassword=12345678&passwordConfirmation=12345678"
    // let url : string = "http://localhost:8080/api/users/checkPass?OldPassword="+this.form2.value.OldPassword+
    //   "&NewPassword="+this.form2.value.NewPassword+
    //   "&passwordConfirmation="+this.form2.value.passwordConfirmation

    return this.http.patch(environment.URL + 'api/users/checkPass',null,{params:queryParams}).subscribe(() =>{
      this.app.openSnackBar("Password changed successfully","OK")
    },error => {
      this.app.openSnackBar("Old password is not correct","OK")
      this.form.enable()
    })

  }

  onSearchTextEntered(searchValue: string){
    this.searchText = searchValue;
  }

  follow(id){
    this.http.post(environment.URL + 'follows', {"teamId": id,
      "userId": this.userId
    })
      .subscribe(() => {
        this.followedTeams2.push(id)
      });
  }

  delete(teamId) {
    this.http.delete(environment.URL + 'follows/' + this.userId + '?teamId=' + teamId)
      .subscribe(() => {
        this.followedTeams2 = this.followedTeams2.filter(function(value, index, arr){
          return value != teamId;
        })
      });
  }

  ngOnInit(): void {
    this.getUserFromToken()
    this.http.get(environment.URL + 'api/pictures/getUserProfileImage',{responseType : 'text'}).subscribe(responseData =>{this.profileUrl = responseData})
    this.http.get(environment.URL + 'teams').subscribe(responseData =>{this.teams = responseData
      console.log(this.teams)
      this.http.get(environment.URL + 'follows?userId=' + this.userId)
        .subscribe((Response) => {
          this.followedTeams = <Array<any>>Response
          console.log(this.followedTeams)
          for (let i = 0; i < this.followedTeams.length; i++){
            this.followedTeams2.push(this.followedTeams[i]['teamId'])
          }
          console.log(this.followedTeams2)
        });
    })

    this.form = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required])
    })

    this.form2 = new FormGroup({
      OldPassword: new FormControl('', [Validators.required]),
      NewPassword: new FormControl('', [Validators.required]),
      passwordConfirmation: new FormControl('', [Validators.required])
    })

    this.form3 = new FormGroup({
      OldPassword: new FormControl('', [Validators.required]),
      NewPassword: new FormControl('', [Validators.required]),
      passwordConfirmation: new FormControl('', [Validators.required])
    })
  }

}
