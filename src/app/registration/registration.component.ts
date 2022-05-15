import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {HttpParams} from "@angular/common/http";
import {AuthService} from "../services/auth.service";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit, OnDestroy{

  // form: FormGroup
  aSub: Subscription

  // @Input() formError = 'Error';

  hide=true;

  form = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)])
  })

  constructor(private auth: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.auth.logout()

  }

  ngOnDestroy() {
    if(this.aSub){
      this.aSub.unsubscribe()
    }
  }

  onSubmit(){
    // this.form.disable()

    this.aSub = this.auth.register(this.form.value).subscribe(
      ()=> {
        console.log('Register success')
        this.router.navigate(['/login'], {
          queryParams: {
            registered: true
          }
        })
      },
      error =>{
        console.warn(error)
        this.form.enable()
      }
    )
  }

  onFormChange(){
    // this.formError = '';
  }

}

