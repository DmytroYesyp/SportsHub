import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router, Params} from '@angular/router';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {HttpParams} from "@angular/common/http";
import {Subscription} from "rxjs";
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition} from "@angular/material/snack-bar";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy{

  // @Input() formError = 'Error';

  hide=true;
  // email: string
  // password: string

  // form: FormGroup;
  aSub: Subscription

  form = new FormGroup({
      email: new FormControl('', [Validators.email, Validators.required]),
      password: new FormControl('', [Validators.required, Validators.minLength(8)])
    }
  );

  horizontalPosition: MatSnackBarHorizontalPosition = 'right';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  constructor(private auth: AuthService,
              private router: Router,
              private route: ActivatedRoute,
              private snackBar: MatSnackBar
  ) {}

  openSnackBar(message: string, action: string){
    this.snackBar.open(message, action, {horizontalPosition: this.horizontalPosition, verticalPosition: this.verticalPosition});
  }

  ngOnInit(): void {
    this.auth.logout()

    this.route.queryParams.subscribe((params: Params) => {
      if (params['registered']){
        // alert("Now you registered")
        this.openSnackBar("Now you registered", "Ok")
      }else if(params['accessDenied']){
        // alert("You must be authorized to access this page")
        this.openSnackBar("You must be authorized to access this page", "Login")
      } else if(params['sessionFailed']){
        // alert("Login again")
        this.openSnackBar("Login again", "Ok")
      }
    })

  }

  onFormChange(){
    // this.formError = '';
  }

  // gerErrorMessage(){
  //   if(this.form.hasError('required')){
  //     return "Not valid"
  //   }
  //   return this.form.hasError('required') ? 'Not valid' : '';
  // }

  onSubmit(){
    // let body = new URLSearchParams();
    // body.set('email', this.form.value.email);
    // body.set('password', this.form.value.password);



    let params = new HttpParams({fromObject:{email:this.form.value.email, password:this.form.value.password}})

    //let body = `email=${this.form.value.email}&password=${this.form.value.password}`;


    // alert(params)
    // this.form.disable()

      this.aSub = this.auth.login(params).subscribe(
        () => {
          console.log('Login success')
          this.router.navigate(['/main'])
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
