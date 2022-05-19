import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetLangComponent } from './set-lang.component';

describe('SetLangComponent', () => {
  let component: SetLangComponent;
  let fixture: ComponentFixture<SetLangComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetLangComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SetLangComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
