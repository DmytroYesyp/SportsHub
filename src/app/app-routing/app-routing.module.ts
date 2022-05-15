import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {CommonModule} from "@angular/common";
import {RegistrationComponent} from "../registration/registration.component";
import {MainPageComponent} from "../main-page/main-page.component";
import {LoginComponent} from "../login/login.component";
import {ProfileComponent} from "../profile/profile.component";
import {AuthLayoutComponent} from "../layouts/auth-layout/auth-layout.component";
import {SiteLayoutComponent} from "../layouts/site-layout/site-layout.component";
import {AuthGuard} from "../classes/auth.guard";
import {AdminPageComponent} from "../admin-page/admin-page.component";
import {ForgotPasswordComponent} from "../forgot-password/forgot-password.component";
import {ResetPasswordComponent} from "../reset-password/reset-password.component";
import {ArticleEditorComponent} from "../article-editor/article-editor.component";

const routes: Routes = [
  {
    path: '', component: AuthLayoutComponent, children:[
      {path: '', redirectTo: '/main', pathMatch: 'full'},
      {path: 'login', component: LoginComponent},
      {path: 'register', component: RegistrationComponent},
      {path: 'forgot_password', component: ForgotPasswordComponent},
      {path: 'reset_password', component: ResetPasswordComponent},
      {path: 'main', component: MainPageComponent},
    ]
  },
  {
    path: '', component: SiteLayoutComponent, canActivate: [AuthGuard], children:[
      {path: 'profile', component: ProfileComponent},
      {path: 'admin_page', component: AdminPageComponent},
      {path: 'article_editor', component: ArticleEditorComponent}
    ]
  }
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
