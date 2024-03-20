import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddHeroesFormComponent } from './add-heroes-form.component';

describe('AddHeroesFormComponent', () => {
  let component: AddHeroesFormComponent;
  let fixture: ComponentFixture<AddHeroesFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddHeroesFormComponent]
    });
    fixture = TestBed.createComponent(AddHeroesFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
