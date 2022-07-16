import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamhubComponent } from './teamhub.component';

describe('TeamhubComponent', () => {
  let component: TeamhubComponent;
  let fixture: ComponentFixture<TeamhubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamhubComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamhubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
