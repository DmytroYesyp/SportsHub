import {Component, Input, OnInit} from '@angular/core';
import {mainPage} from "../services/main-page.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  isVisible: any = 0;
  isSelected: boolean = true;
  form: FormGroup

  arr :string[] = ["My surveys","My teamhub","Log out"]
  arr2 :string[]  = ["","","/register"]

  email: string;

  user: Object;
  constructor(private auth:AuthService) {
  }



  onSubmit(){
    this.auth.updateUser(this.form.value)
    setTimeout(function (){window.location.reload()},500)
  }


  ngOnInit(): void {

    this.form = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required])
    })

  }

}
