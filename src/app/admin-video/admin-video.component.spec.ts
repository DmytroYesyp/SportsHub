import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminVideoComponent } from './admin-video.component';

describe('AdminVideoComponent', () => {
  let component: AdminVideoComponent;
  let fixture: ComponentFixture<AdminVideoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminVideoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminVideoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
