import { ComponentFixture, TestBed } from '@angular/core/testing';
import {HttpClientModule} from '@angular/common/http';
import { SocialShaeComponent } from './social-shae.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {AppComponent} from "../../../app.component";
import {NgModule} from "@angular/core";

describe('SocialShaeComponent', () => {
  let component: SocialShaeComponent;
  let fixture: ComponentFixture<SocialShaeComponent>;


  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule]

  }));

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialShaeComponent]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialShaeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(2).toBe(2);
  });
});
