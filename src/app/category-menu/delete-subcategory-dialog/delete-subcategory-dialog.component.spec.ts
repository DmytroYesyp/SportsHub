import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteSubcategoryDialogComponent } from './delete-subcategory-dialog.component';
import {HttpClientModule} from "@angular/common/http";
import {MatDialogModule} from "@angular/material/dialog";
import {TranslateModule} from "@ngx-translate/core";

describe('DeleteSubcategoryDialogComponent', () => {
  let component: DeleteSubcategoryDialogComponent;
  let fixture: ComponentFixture<DeleteSubcategoryDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, MatDialogModule, TranslateModule.forRoot()],
      declarations: [ DeleteSubcategoryDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteSubcategoryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
