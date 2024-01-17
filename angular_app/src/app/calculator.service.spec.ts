import { TestBed } from '@angular/core/testing';

import { CalculatorService } from './calculator.service';

// Ici on test un service, les

describe('CalculatorService', () => {
  let service: CalculatorService;

  beforeEach(() => {
    // On configure l'environnement de test avec le CalculatorService
    TestBed.configureTestingModule({ providers: [CalculatorService] });
    // On récupère l'instance de CalculatorService
    service = TestBed.inject(CalculatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("Should calculate double of values", () => {
    // Test de la fonction double du service
    const arr = [1, 2, 3];
    expect(service.double(arr)).toEqual([2, 4, 6]);
  });



  it("Should calculate double of values", () => {
    // Test de la fonction doubleFetch 
    // où nous mockons la valeur de retour de la fonction fetchArr qui est appeller dans doubleFetch
    // Voir le calculator service
    const stubValue = [1, 2, 3];
    const fetchArrSpy = spyOn(service, "fetchArr").and.returnValue(stubValue);
    expect(service.doubleFetch()).toEqual([2, 4, 6]);
    expect(fetchArrSpy).toHaveBeenCalled();
    expect(fetchArrSpy).toHaveBeenCalledTimes(1);
  });
});
