import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaguePageComponent } from './league-page.component';

describe('LeaguePageComponent', () => {
  let component: LeaguePageComponent;
  let fixture: ComponentFixture<LeaguePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeaguePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LeaguePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
