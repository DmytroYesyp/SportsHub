import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupNewsletterSuccessComponent } from './popup-newsletter-success.component';

describe('PopupNewsletterSuccessComponent', () => {
  let component: PopupNewsletterSuccessComponent;
  let fixture: ComponentFixture<PopupNewsletterSuccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupNewsletterSuccessComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupNewsletterSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
