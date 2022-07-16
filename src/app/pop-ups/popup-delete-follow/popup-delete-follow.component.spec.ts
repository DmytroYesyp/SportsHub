import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupDeleteFollowComponent } from './popup-delete-follow.component';

describe('PopupDeleteFollowComponent', () => {
  let component: PopupDeleteFollowComponent;
  let fixture: ComponentFixture<PopupDeleteFollowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupDeleteFollowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupDeleteFollowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
