import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {AuthService} from "../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})

export class ResetPasswordComponent implements OnInit {

  hide=true;
  // email: string
  // password: string

  form: FormGroup;


  constructor(private auth: AuthService,
              private router: Router,
              private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
        newPassword: new FormControl('', [Validators.required, Validators.minLength(8)]),
        confirmPassword: new FormControl('', [Validators.required, Validators.minLength(8)])
      }
    );
  }



  onSubmit(){
    // let body = new URLSearchParams();
    // body.set('email', this.form.value.email);
    // body.set('password', this.form.value.password);


    let token = this.auth.parse();

    let params = new HttpParams({fromObject:{token:token, newPassword:this.form.value.newPassword, confirmPassword:this.form.value.confirmPassword}})

    // console.log(params)
    //let body = `email=${this.form.value.email}&password=${this.form.value.password}`;


    // alert(params)


    this.auth.reset_password(params).subscribe(
      () => {
        console.log('I am here')
        this.router.navigate(['/login'])
      },
      error => {
        console.log("Syka")
        console.warn(error)
      }
    )
  }

}




