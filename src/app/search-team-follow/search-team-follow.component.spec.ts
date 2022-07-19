import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchTeamFollowComponent } from './search-team-follow.component';

describe('SearchTeamFollowComponent', () => {
  let component: SearchTeamFollowComponent;
  let fixture: ComponentFixture<SearchTeamFollowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchTeamFollowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchTeamFollowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
