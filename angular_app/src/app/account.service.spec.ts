import { TestBed } from '@angular/core/testing';

import { AccountService } from './account.service';
import { CalculatorService } from './calculator.service';

// Here we mock a full service 

describe('AccountService', () => {
  let service: AccountService;

  // Typer le spy
  let calculatorServiceSpy: jasmine.SpyObj<CalculatorService>; 

  beforeEach(() => {
    // Objet spy pour le service CalculatorService
    const spy = jasmine.createSpyObj("CalculatorService", ["double"]);

    // On configure l'environnement de test avec les 2 providers
    // useValue means when the calculatorService is injected in the service the spy will be used instead of the real double implementation
    TestBed.configureTestingModule({ providers: [AccountService, {provide: CalculatorService, useValue: spy}]  });
    // Le service tester est injectée grâce à l'injection de dépendance d'angular
    service = TestBed.inject(AccountService);

    // De même pour le mock de CalculatorService
    calculatorServiceSpy = TestBed.inject(CalculatorService) as jasmine.SpyObj<CalculatorService>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("Should return the final value added", () => {
    const stubValue = [2,4,6];
    calculatorServiceSpy.double.and.returnValue(stubValue)
    expect(service.getFinalValue()).toBe(12);
  })
});
