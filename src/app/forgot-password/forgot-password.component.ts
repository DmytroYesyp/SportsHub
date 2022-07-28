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
    this.loading=true;




    let param = new HttpParams({fromObject:{email:this.form.value.email}})



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



