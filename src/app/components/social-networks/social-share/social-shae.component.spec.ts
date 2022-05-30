import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialShaeComponent } from './social-shae.component';

describe('SocialShaeComponent', () => {
  let component: SocialShaeComponent;
  let fixture: ComponentFixture<SocialShaeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SocialShaeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialShaeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
