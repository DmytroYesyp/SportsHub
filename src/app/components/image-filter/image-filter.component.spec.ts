import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageFilterComponent } from './image-filter.component';

describe('ImageFilterComponent', () => {
  let component: ImageFilterComponent;
  let fixture: ComponentFixture<ImageFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImageFilterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
