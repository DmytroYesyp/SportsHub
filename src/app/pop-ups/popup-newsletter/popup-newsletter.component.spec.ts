import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupNewsletterComponent } from './popup-newsletter.component';

describe('PopupNewsletterComponent', () => {
  let component: PopupNewsletterComponent;
  let fixture: ComponentFixture<PopupNewsletterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupNewsletterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupNewsletterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
