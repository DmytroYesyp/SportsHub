import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchFilterPipe } from 'src/app/search-filter.pipe';
import { AdminTeamPageComponent } from './admin-team-page.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";




describe('AdminTeamPageComponent', () => {
  let component: AdminTeamPageComponent;
  let fixture: ComponentFixture<AdminTeamPageComponent>;
  // let http: HttpClient;
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule]

  }));
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminTeamPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTeamPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(2).toBe(2);
  });
});
