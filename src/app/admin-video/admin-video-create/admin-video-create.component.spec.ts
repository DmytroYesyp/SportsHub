import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminVideoCreateComponent } from './admin-video-create.component';

describe('AdminVideoCreateComponent', () => {
  let component: AdminVideoCreateComponent;
  let fixture: ComponentFixture<AdminVideoCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminVideoCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminVideoCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
