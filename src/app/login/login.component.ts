import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router, Params} from '@angular/router';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {HttpParams} from "@angular/common/http";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy{

  @Input() formError = 'Error';

  hide=true;
  // email: string
  // password: string

  form: FormGroup;
  aSub: Subscription

  constructor(private auth: AuthService,
              private router: Router,
              private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
        email: new FormControl('', [Validators.email, Validators.required]),
        password: new FormControl('', [Validators.required, Validators.minLength(8)])
      }
    );

    this.route.queryParams.subscribe((params: Params) => {
      if (params['registered']){
        alert("Now you registered")
      }else if(params['accessDenied']){
        alert("You must be authorized to access this page")
      } else if(params['sessionFailed']){
        alert("Login again")
      }
    })

  }

  onFormChange(){
    this.formError = '';
  }

  onSubmit(){
    // let body = new URLSearchParams();
    // body.set('email', this.form.value.email);
    // body.set('password', this.form.value.password);



    let params = new HttpParams({fromObject:{email:this.form.value.email, password:this.form.value.password}})

    //let body = `email=${this.form.value.email}&password=${this.form.value.password}`;


    // alert(params)
    this.form.disable()

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
