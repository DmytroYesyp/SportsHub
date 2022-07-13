import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetLangComponent } from './set-lang.component';
import {HttpClientModule} from "@angular/common/http";
import {MatDialogModule} from "@angular/material/dialog";
import {TranslateModule} from "@ngx-translate/core";

describe('SetLangComponent', () => {
  let component: SetLangComponent;
  let fixture: ComponentFixture<SetLangComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, MatDialogModule, TranslateModule.forRoot()],
      declarations: [ SetLangComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SetLangComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
