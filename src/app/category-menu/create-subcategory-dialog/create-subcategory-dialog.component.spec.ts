import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSubcategoryDialogComponent } from './create-subcategory-dialog.component';
import {HttpClientModule} from "@angular/common/http";
import {MatDialogModule} from "@angular/material/dialog";
import {TranslateModule} from "@ngx-translate/core";

describe('CreateSubcategoryDialogComponent', () => {
  let component: CreateSubcategoryDialogComponent;
  let fixture: ComponentFixture<CreateSubcategoryDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, MatDialogModule, TranslateModule.forRoot()],
      declarations: [ CreateSubcategoryDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSubcategoryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
