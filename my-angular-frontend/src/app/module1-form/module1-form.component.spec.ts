import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Module1FormComponent } from './module1-form.component';

describe('Module1FormComponent', () => {
  let component: Module1FormComponent;
  let fixture: ComponentFixture<Module1FormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Module1FormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Module1FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
