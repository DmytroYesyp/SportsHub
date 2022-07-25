import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDealbookComponent } from './admin-dealbook.component';

describe('AdminDealbookComponent', () => {
  let component: AdminDealbookComponent;
  let fixture: ComponentFixture<AdminDealbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminDealbookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminDealbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
