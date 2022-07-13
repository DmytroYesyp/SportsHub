import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupDeleteCommComponent } from './popup-delete-comm.component';

describe('PopupDeleteCommComponent', () => {
  let component: PopupDeleteCommComponent;
  let fixture: ComponentFixture<PopupDeleteCommComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupDeleteCommComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupDeleteCommComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
