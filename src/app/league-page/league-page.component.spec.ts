import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaguePageComponent } from './league-page.component';
import {HttpClientModule} from "@angular/common/http";
import {MatDialogModule} from "@angular/material/dialog";
import {TranslateModule} from "@ngx-translate/core";

describe('LeaguePageComponent', () => {
  let component: LeaguePageComponent;
  let fixture: ComponentFixture<LeaguePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, MatDialogModule, TranslateModule.forRoot()],
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
