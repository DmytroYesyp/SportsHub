import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminMainArticleComponent } from './admin-main-article.component';

describe('AdminMainArticleComponent', () => {
  let component: AdminMainArticleComponent;
  let fixture: ComponentFixture<AdminMainArticleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminMainArticleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminMainArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
