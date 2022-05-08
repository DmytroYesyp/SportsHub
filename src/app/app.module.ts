import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
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
import { ImageEditorComponent } from './components/image-editor/image-editor.component';
import { ArticleEditorComponent } from './article-editor/article-editor.component';
import { ImageFilterComponent } from './components/image-filter/image-filter.component';
import { DragDropComponent } from './components/drag-drop/drag-drop.component';
import { UploaderComponent } from './components/uploader/uploader.component';
import { UploadTaskComponent } from './components/upload-task/upload-task.component';
import {AngularFireModule} from "@angular/fire/compat";
import {AngularFireStorageModule} from "@angular/fire/compat/storage";
import {AngularFireAuthModule} from "@angular/fire/compat/auth";
import {AngularFirestoreModule} from "@angular/fire/compat/firestore";
import { DropzoneDirective } from './dropzone.directive';
import { initializeApp,provideFirebaseApp } from '@angular/fire/app';
import { environment } from '../environments/environment';
import { provideAuth,getAuth } from '@angular/fire/auth';
import { provideFirestore,getFirestore } from '@angular/fire/firestore';
import { provideStorage,getStorage } from '@angular/fire/storage';



const config = {
  apiKey: "AIzaSyAPBCRevQcuxSyHqiXMsFTgUU7bBjZbIPI",
  authDomain: "sportshub-623db.firebaseapp.com",
  projectId: "sportshub-623db",
  storageBucket: "sportshub-623db.appspot.com",
  messagingSenderId: "524872866401",
  appId: "1:524872866401:web:d517a9efe5450869322820",
  measurementId: "G-HK6SH6ZJ3J"
};



@NgModule({
  declarations: [
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
    ImageEditorComponent,
    ArticleEditorComponent,
    ImageFilterComponent,
    DragDropComponent,
    UploaderComponent,
    UploadTaskComponent,
    DropzoneDirective
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFirestoreModule,
    AngularFireAuthModule,
    AngularFireStorageModule,
    // provideFirebaseApp(() => initializeApp(environment.firebase)),
    // provideAuth(() => getAuth()),
    // provideFirestore(() => getFirestore()),
    // provideStorage(() => getStorage())
  ],
  providers: [
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
