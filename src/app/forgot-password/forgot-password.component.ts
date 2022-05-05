import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {AuthService} from "../services/auth.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";

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

  constructor(private auth: AuthService,
              private router: Router,
              private route: ActivatedRoute
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



    let param = new HttpParams({fromObject:{email:this.form.value.email}})

    //let body = `email=${this.form.value.email}&password=${this.form.value.password}`;


    // alert(params)


    this.aSub = this.auth.forgot_password(param).subscribe(
      () => {
        console.log('Email send')
        this.success = true;
      },
      error => {
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



