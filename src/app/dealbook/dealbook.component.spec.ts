import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DealbookComponent } from './dealbook.component';

describe('DealbookComponent', () => {
  let component: DealbookComponent;
  let fixture: ComponentFixture<DealbookComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DealbookComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DealbookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
