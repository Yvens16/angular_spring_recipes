import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroesListBetterComponent } from './heroes-list-better.component';

describe('HeroesListBetterComponent', () => {
  let component: HeroesListBetterComponent;
  let fixture: ComponentFixture<HeroesListBetterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeroesListBetterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HeroesListBetterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
