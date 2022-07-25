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
import {AdminTeamPageComponent} from "../admin-team-page/admin-team-page.component";
import {AdminLayoutComponent} from "../layouts/admin-layout/admin-layout.component";
import {AdminGuard} from "../classes/admin.guard";
import {ArticleComponent} from "../article/article.component";
import {SetLangComponent} from "../set-lang/set-lang.component";
import {NavMenuItemsComponent} from "../category-menu/nav-menu-items/nav-menu-items.component";
import {AddArticleComponent} from "../admin-article/add-article/add-article.component";
import {GoogleRegisterComponent} from "../login/OAuth/google-register/google-register.component";
import {GoogleLoginComponent} from "../login/OAuth/google-login/google-login.component";
import {VideoComponent} from "../video/video.component";
import {SocialLoginComponent} from "../components/social-networks/social-login/social-login.component";
import {TeamPageComponent} from "../team-page/team-page.component";
import {LeaguePageComponent} from "../league-page/league-page.component";
import {TeamhubComponent} from "../teamhub/teamhub.component";
import {EditArticleComponent} from "../admin-article/edit-article/edit-article.component";
import {AdminArticleListComponent} from "../admin-article/admin-article-list/admin-article-list.component";
import {AdminVideoComponent} from "../admin-video/admin-video.component";
import {AdminVideoCreateComponent} from "../admin-video/admin-video-create/admin-video-create.component";
import {AdminDealbookComponent} from "../admin-article/admin-dealbook/admin-dealbook.component";
import {AdminLifestyleComponent} from "../admin-article/admin-lifestyle/admin-lifestyle.component";
import {DealbookComponent} from "../dealbook/dealbook.component";
import {LifestyleComponent} from "../lifestyle/lifestyle.component";

const routes: Routes = [
  {
    path: '', component: AuthLayoutComponent, children:[
      {path: '', redirectTo: '/main', pathMatch: "full"},
      {path: 'login', component: LoginComponent},
      {path: 'google_register', component: GoogleRegisterComponent},
      {path: 'google_login', component: GoogleLoginComponent},
      {path: 'register', component: RegistrationComponent},
      {path: 'forgot_password', component: ForgotPasswordComponent},
      {path: 'reset_password', component: ResetPasswordComponent},
      {path: 'main', component: MainPageComponent},
      {path: 'article/:newsId', component: ArticleComponent},
      {path: 'video', component: VideoComponent},
      {path: 'admin_page/social-login', component: SocialLoginComponent},
      {path: 'team/:teamId', component: TeamPageComponent},
      {path: 'league/:leagueId', component: LeaguePageComponent},
      {path: 'team_hub', component: TeamhubComponent},
      {path: 'dealbook', component: DealbookComponent},
      {path: 'lifestyle', component: LifestyleComponent}

    ]
  },
  {
    path: '', component: SiteLayoutComponent, canActivate: [AuthGuard], children:[
      {path: 'profile', component: ProfileComponent}
    ]
  },
  {
    path: '', component: AdminLayoutComponent, canActivate: [AdminGuard], children:[
      {path: 'admin_page', component: AdminPageComponent},
      {path: 'admin_categories', component: NavMenuItemsComponent},
      {path: 'article_add', component: AddArticleComponent},
      {path: 'article_edit/:id', component: EditArticleComponent},
      {path: 'admin_get_article/:sportKindId', component: AdminArticleListComponent},
      {path: 'article_editor', component: ArticleEditorComponent},
      {path: 'admin_team-page', component: AdminTeamPageComponent},
      {path: 'admin_page/set-lang', component: SetLangComponent},
      {path: 'admin_video', component: AdminVideoComponent},
      {path: 'admin_video/create', component: AdminVideoCreateComponent},
      {path: 'admin_dealbook', component: AdminDealbookComponent},
      {path: 'admin_lifestyle', component: AdminLifestyleComponent}
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
