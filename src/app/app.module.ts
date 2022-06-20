import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { MainPageComponent } from './main-page/main-page.component';

import {MaterialModule} from "./material/material.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { LoginComponent } from './login/login.component';
import {TokenInterceptor} from "./classes/token.interceptor";
import { ProfileComponent } from './profile/profile.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';
import { SiteLayoutComponent } from './layouts/site-layout/site-layout.component';
import {MainKindsComponent} from "./services/main_kinds.component";
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import {AuthService} from "./services/auth.service";
import {HeaderUserProfileComponent} from "./components/header-user-profile/header-user-profile.component";
import {AdminPageComponent} from "./admin-page/admin-page.component";
import {NewsCardComponent} from "./services/news_card.component";
import { ArticleEditorComponent } from './article-editor/article-editor.component';
import { UploaderComponent } from './components/uploader/uploader.component';
import {AngularFireModule} from "@angular/fire/compat";
import {AngularFireStorageModule} from "@angular/fire/compat/storage";
import {AngularFireAuthModule} from "@angular/fire/compat/auth";
import {AngularFirestoreModule} from "@angular/fire/compat/firestore";
import { RichTextEditorModule } from '@syncfusion/ej2-angular-richtexteditor';
import { environment } from '../environments/environment';
import {NgpImagePickerModule} from "ngp-image-picker";
import {AdminTeamPageComponent} from "./admin-team-page/admin-team-page.component";
import { SearchFilterPipe } from './search-filter.pipe';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { ArticleComponent } from './article/article.component';
import { LangComponent } from './components/lang/lang.component';
import { SetLangComponent } from './set-lang/set-lang.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import { CreateCategoryDialogComponent } from './category-menu/create-category-dialog/create-category-dialog.component';
import { CreateSubcategoryDialogComponent } from './category-menu/create-subcategory-dialog/create-subcategory-dialog.component';
import { CreateTeamDialogComponent } from './category-menu/create-team-dialog/create-team-dialog.component';
import { DeleteCategoryDialogComponent } from './category-menu/delete-category-dialog/delete-category-dialog.component';
import { DeleteSubcategoryDialogComponent } from './category-menu/delete-subcategory-dialog/delete-subcategory-dialog.component';
import { DeleteTeamDialogComponent } from './category-menu/delete-team-dialog/delete-team-dialog.component';
import { EditCategoryDialogComponent } from './category-menu/edit-category-dialog/edit-category-dialog.component';
import { EditSubcategoryDialogComponent } from './category-menu/edit-subcategory-dialog/edit-subcategory-dialog.component';
import { EditTeamDialogComponent } from './category-menu/edit-team-dialog/edit-team-dialog.component';
import { NavMenuItemsComponent } from './category-menu/nav-menu-items/nav-menu-items.component';
import { AddArticleComponent } from './admin-article/add-article/add-article.component';
import { SocialShaeComponent } from './components/social-networks/social-share/social-shae.component';
import { SocialFollowComponent } from './components/social-networks/social-follow/social-follow.component';
import { SocialLoginComponent } from './components/social-networks/social-login/social-login.component';
import { SocialLoginModule, SocialAuthServiceConfig } from '@abacritt/angularx-social-login';
import {GoogleLoginProvider, FacebookLoginProvider} from '@abacritt/angularx-social-login';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    MainKindsComponent,
    NewsCardComponent,
    AppComponent,
    RegistrationComponent,
    MainPageComponent,
    LoginComponent,
    ProfileComponent,
    AuthLayoutComponent,
    SiteLayoutComponent,
    MainKindsComponent,
    ResetPasswordComponent,
    ForgotPasswordComponent,
    ProfileComponent,
    HeaderUserProfileComponent,
    AdminPageComponent,
    ArticleEditorComponent,
    UploaderComponent,
    AdminTeamPageComponent,
    SearchFilterPipe,
    AdminLayoutComponent,
    ArticleComponent,
    LangComponent,
    SetLangComponent,
    AdminTeamPageComponent,
    CreateCategoryDialogComponent,
    CreateSubcategoryDialogComponent,
    CreateTeamDialogComponent,
    DeleteCategoryDialogComponent,
    DeleteSubcategoryDialogComponent,
    DeleteTeamDialogComponent,
    EditCategoryDialogComponent,
    EditSubcategoryDialogComponent,
    EditTeamDialogComponent,
    NavMenuItemsComponent,
    AddArticleComponent,
    SocialShaeComponent,
    SocialFollowComponent,
    SocialLoginComponent,

  ],

  imports: [
    SocialLoginModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      },
      defaultLanguage: 'en'
    }),
    MatCheckboxModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFirestoreModule,
    AngularFireAuthModule,
    AngularFireStorageModule,
    RichTextEditorModule,
    NgpImagePickerModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      },
      defaultLanguage: 'en'
    }),
    MatCheckboxModule
    // provideFirebaseApp(() => initializeApp(environment.firebase)),
    // provideAuth(() => getAuth()),
    // provideFirestore(() => getFirestore()),
    // provideStorage(() => getStorage())
  ],
  providers: [
    AuthService,
    AppComponent,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: FacebookLoginProvider.PROVIDER_ID,
            provider: new FacebookLoginProvider(
              '2851340391840240'
            )
          }
        ]
      } as SocialAuthServiceConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
