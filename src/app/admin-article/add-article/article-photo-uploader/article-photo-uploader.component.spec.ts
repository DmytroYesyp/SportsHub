import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticlePhotoUploaderComponent } from './article-photo-uploader.component';

describe('ArticlePhotoUploaderComponent', () => {
  let component: ArticlePhotoUploaderComponent;
  let fixture: ComponentFixture<ArticlePhotoUploaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticlePhotoUploaderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticlePhotoUploaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
