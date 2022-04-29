import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing/app-routing.module";
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { MainPageComponent } from './main-page/main-page.component';

import {MaterialModule} from "./material/material.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { LoginComponent } from './login/login.component';
import {MatSelectModule} from "@angular/material/select";
import {MatExpansionModule} from "@angular/material/expansion";
import {MainKindsComponent} from "./services/main_kinds.component";

@NgModule({
  declarations: [
    MainKindsComponent,
    AppComponent,
    RegistrationComponent,
    MainPageComponent,
    LoginComponent,
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        MaterialModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        MatSelectModule,
        MatExpansionModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
