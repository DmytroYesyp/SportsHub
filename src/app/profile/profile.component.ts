import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {HttpClient, HttpParams} from "@angular/common/http";
import {AppComponent} from "../app.component";

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
  imageAdd: boolean = false;
  profileUrl: string;

  UserProfileUpload : string = 'UserProfileUpload';


  arr :string[] = ["My surveys","My teamhub","Log out"]
  arr2 :string[]  = ["","/team_hub", "/register"]
  email: string;
  user: Object;
  constructor(private auth:AuthService,
              private http:HttpClient,
              private app: AppComponent) {
  }



  Submit(){
    this.auth.updateUser(this.form.value)
    setTimeout(function (){window.location.reload()},500)
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

    return this.http.patch('http://localhost:8080/api/users/checkPass',null,{params:queryParams}).subscribe(() =>{
      this.app.openSnackBar("Password changed successfully","OK")
    },error => {
      this.app.openSnackBar("Old password is not correct","OK")
      this.form.enable()
    })

  }


  ngOnInit(): void {
    this.http.get('http://localhost:8080/api/pictures/getUserProfileImage',{responseType : 'text'}).subscribe(responseData =>{this.profileUrl = responseData})
    this.form = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required])
    })

    this.form2 = new FormGroup({
      OldPassword: new FormControl('', [Validators.required]),
      NewPassword: new FormControl('', [Validators.required]),
      passwordConfirmation: new FormControl('', [Validators.required])
    })
  }

}
