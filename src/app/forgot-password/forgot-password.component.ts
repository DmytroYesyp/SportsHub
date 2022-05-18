import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {AuthService} from "../services/auth.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {AppComponent} from "../app.component";
import {ProgressSpinnerMode} from "@angular/material/progress-spinner";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {


  hide=true;
  // email: string
  // password: string

  form: FormGroup;
  aSub: Subscription
  success: boolean = false;
  loading: boolean = false;

  color: "red"
  mode: ProgressSpinnerMode = 'determinate';

  constructor(private auth: AuthService,
              private router: Router,
              private route: ActivatedRoute,
              private app: AppComponent
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
        email: new FormControl('', [Validators.email, Validators.required])
      }
    );
  }



  onSubmit(){
    // let body = new URLSearchParams();
    // body.set('email', this.form.value.email);
    // body.set('password', this.form.value.password);
    this.loading=true;




    let param = new HttpParams({fromObject:{email:this.form.value.email}})

    //let body = `email=${this.form.value.email}&password=${this.form.value.password}`;


    // alert(params)


    this.aSub = this.auth.forgot_password(param).subscribe(
      () => {
        this.loading=false;
        this.success = true;
        console.log('Email send')
      },
      error => {
        this.loading=false;
        this.app.openSnackBar("This email is not exist", "Try again")
        console.warn(error)
      }
    )
  }

  ngOnDestroy() {
    if(this.aSub){
      this.aSub.unsubscribe()
    }
  }

}



