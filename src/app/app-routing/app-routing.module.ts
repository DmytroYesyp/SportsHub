import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {CommonModule} from "@angular/common";
import {RegistrationComponent} from "../registration/registration.component";
import {MainPageComponent} from "../main-page/main-page.component";
import {LoginComponent} from "../login/login.component";

const routes: Routes = [
  {path: 'register', component: RegistrationComponent,},
  {path: '', component: MainPageComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule,
    RouterModule.forRoot(routes)
  ],
  exports:[
    RouterModule
  ]
}
)
export class AppRoutingModule{}
