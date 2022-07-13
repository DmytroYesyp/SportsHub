import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupLoginCommComponent } from './popup-login-comm.component';

describe('PopupLoginCommComponent', () => {
  let component: PopupLoginCommComponent;
  let fixture: ComponentFixture<PopupLoginCommComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupLoginCommComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupLoginCommComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
