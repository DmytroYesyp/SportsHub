import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminLifestyleComponent } from './admin-lifestyle.component';

describe('AdminLifestyleComponent', () => {
  let component: AdminLifestyleComponent;
  let fixture: ComponentFixture<AdminLifestyleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminLifestyleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminLifestyleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
