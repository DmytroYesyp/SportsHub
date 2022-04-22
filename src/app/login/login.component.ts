import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() formError = 'Error';

  hide=true;
  // email: string
  // password: string

  form: FormGroup;

  constructor(private auth: AuthService) { }

  ngOnInit(): void {
    this.form = new FormGroup({
        email: new FormControl('', [Validators.email, Validators.required]),
        password: new FormControl('', [Validators.required])
      }
    );
  }

  onFormChange(){
    this.formError = '';
  }

  onSubmit(){
    this.auth.login(this.form.value).subscribe(
      ()=> console.log('Login success'),
      error=>{
        console.warn(error)
      }
    )
  }


}
