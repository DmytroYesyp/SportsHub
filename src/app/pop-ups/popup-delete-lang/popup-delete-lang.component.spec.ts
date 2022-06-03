import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupDeleteLangComponent } from './popup-delete-lang.component';

describe('PopupDeleteLangComponent', () => {
  let component: PopupDeleteLangComponent;
  let fixture: ComponentFixture<PopupDeleteLangComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupDeleteLangComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupDeleteLangComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
