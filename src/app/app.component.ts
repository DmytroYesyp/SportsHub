import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition} from "@angular/material/snack-bar";

@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>'

})
export class AppComponent implements OnInit{
  title = 'client';

  horizontalPosition: MatSnackBarHorizontalPosition = 'right';
  verticalPosition: MatSnackBarVerticalPosition = 'top';

  constructor(private auth: AuthService,
              private snackBar: MatSnackBar) {
  }

  openSnackBar(message: string, action: string){
    this.snackBar.open(message, action, {horizontalPosition: this.horizontalPosition, verticalPosition: this.verticalPosition});
  }

  ngOnInit() {
    const potentialToken = localStorage.getItem('auth-token')
    if(potentialToken!==null){
      this.auth.setToken(potentialToken)
    }
  }
}
