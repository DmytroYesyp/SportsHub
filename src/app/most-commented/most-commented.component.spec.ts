import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostCommentedComponent } from './most-commented.component';

describe('MostCommentedComponent', () => {
  let component: MostCommentedComponent;
  let fixture: ComponentFixture<MostCommentedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MostCommentedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MostCommentedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
