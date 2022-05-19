
import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {AppComponent} from "../app.component";
import {Injectable} from "@angular/core";
import {Observable, of} from "rxjs";


@Injectable({
  providedIn: 'root'
})

export class AdminGuard implements CanActivate, CanActivateChild{



  constructor(private auth: AuthService,
              private router: Router,
              private app: AppComponent) {

  }

  // func(){
  //   let role = this.app.getUserFromToken()
  //   console.log(role);
  //   return role;
  // }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean>{
    if(this.app.isAdmin()){
      return of(true)
    } else {
      this.router.navigate(['/main'], {
        queryParams:{
          accessDenied: true
        }
      })
      return of(false)
    }
  }

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean>{
    return this.canActivate(route, state)
  }
}
