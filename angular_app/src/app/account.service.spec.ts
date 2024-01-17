import { TestBed } from '@angular/core/testing';

import { AccountService } from './account.service';
import { CalculatorService } from './calculator.service';

// Example de service qui appelle un autre service

describe('AccountService', () => {
  let service: AccountService;

  // Typer le spy
  let calculatorServiceSpy: jasmine.SpyObj<CalculatorService>; 

  beforeEach(() => {
    // Objet spy pour le service CalculatorService
    const spy = jasmine.createSpyObj("CalculatorService", ["double"]);


    // On configure l'environnement de test avec les 2 providers
    // Ici on configure l'environnement de test avec Accountservice et on spécifie qu'on va lui injecter CalculatorService

    // useValue nous permet de remplacer la vrai fonction double de CalculatorService par un mock de la fonction
    // On pourra donc vérifier si la fonction à été appeller et définir la valeur de retour de la fonction voir ligne 38
    TestBed.configureTestingModule({ providers: [AccountService, {provide: CalculatorService, useValue: spy}]  });

    // Le service tester est injectée grâce à l'injection de dépendance d'angular
    // et on peut donc récuperer son instance grâce à TestBed.inject
    service = TestBed.inject(AccountService);

    // De même pour le mock de CalculatorService
    calculatorServiceSpy = TestBed.inject(CalculatorService) as jasmine.SpyObj<CalculatorService>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("Should return the final value added", () => {
    const stubValue = [2,4,6];
    // Ici on décide de la valeur de retour du mock dans le test
    calculatorServiceSpy.double.and.returnValue(stubValue)
    expect(service.getFinalValue()).toBe(12);
  })
});
