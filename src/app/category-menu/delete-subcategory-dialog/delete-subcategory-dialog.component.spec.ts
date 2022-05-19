import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteSubcategoryDialogComponent } from './delete-subcategory-dialog.component';

describe('DeleteSubcategoryDialogComponent', () => {
  let component: DeleteSubcategoryDialogComponent;
  let fixture: ComponentFixture<DeleteSubcategoryDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
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
