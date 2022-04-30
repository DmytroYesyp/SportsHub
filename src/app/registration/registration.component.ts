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

  form: FormGroup
  aSub: Subscription

  @Input() formError = 'Error';

  hide=true;

  constructor(private auth: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.email, Validators.required]),
      password: new FormControl('', [Validators.required, Validators.minLength(8)])
    })
  }

  ngOnDestroy() {
    if(this.aSub){
      this.aSub.unsubscribe()
    }
  }

  onSubmit(){
    this.form.disable()

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
    this.formError = '';
  }

}

//
//   firstName: string
//   lastName: string
//   password: string
//   email: string
//
//
//
//   // user: User = new User('','','','')
//
//   constructor(private http: HttpClient, private auth: AuthService) {}
//
//
//   submit(){
//
//     let url = "http://localhost:8080/api/users/registerUser"
//     this.http.post(url, {
//       firstName:this.firstName,
//       lastName:this.lastName,
//       password:this.password,
//       email:this.email
//     }).toPromise().then((data:any)=>{
//       console.log(data)
//       console.log(JSON.stringify(data.json))
//     })
//   }
// }



//   firstName: string;
//   lastName: string;
//   email: string;
//   password: string;
//   constructor(private http: HttpClient, private router: Router) { }
//
//   ngOnInit(): void {
//     this.firstName = 'levi';
//   }
//   register(firstName, lastName, password, email): boolean {
//     console.log(email.value)
//
//     this.firstName = firstName.value;
//     this.lastName = lastName.value;
//     this.password = password.value;
//     this.email = email.value;
//
//
//     this.http.post<User>('http://localhost:8080/api/users/registerUser', {
//       firstName: this.firstName,
//       lastName: this.lastName,
//       password: this.password,
//       email: this.email
//     }
// )
//       .subscribe(response => {
//         this.router.navigate(['/login']);
//       });
//
//     return false;
//   }


//   form: FormGroup
//   aSub: Subscription
//
//   constructor(private auth: AuthService, private router: Router) {
//   }
//
//   ngOnInit() {
//     this.form = new FormGroup({
//       firstName: new FormControl('', [Validators.required]),
//       lastName: new FormControl('', [Validators.required]),
//       password: new FormControl('', [Validators.required, Validators.minLength(8)]),
//       email: new FormControl('', [Validators.email, Validators.required])
//     })
//   }
//
//   ngOnDestroy() {
//     if(this.aSub){
//       this.aSub.unsubscribe()
//     }
//   }
//
//   onSubmit(){
//     this.form.disable()
//
//     this.aSub = this.auth.register(this.form.value).subscribe(
//       ()=> {
//         console.log('Register success')
//         this.router.navigate([''])
//       },
//       error =>{
//         console.warn(error)
//         this.form.enable()
//       }
//     )
//   }
//
// }
