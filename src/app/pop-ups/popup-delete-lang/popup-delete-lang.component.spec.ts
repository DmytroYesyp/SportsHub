import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupDeleteLangComponent } from './popup-delete-lang.component';
import {HttpClientModule} from "@angular/common/http";
import {MatDialogModule} from "@angular/material/dialog";
import {TranslateModule} from "@ngx-translate/core";

describe('PopupDeleteLangComponent', () => {
  let component: PopupDeleteLangComponent;
  let fixture: ComponentFixture<PopupDeleteLangComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, MatDialogModule, TranslateModule.forRoot()],
      declarations: [ PopupDeleteLangComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupDeleteLangComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
