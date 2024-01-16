import { TestBed } from '@angular/core/testing';

import { CalculatorService } from './calculator.service';

// Here we mock a function of the service

describe('CalculatorService', () => {
  let service: CalculatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({ providers: [CalculatorService] });
    service = TestBed.inject(CalculatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("Should calculate double of values", () => {
    const arr = [1, 2, 3];
    expect(service.double(arr)).toEqual([2, 4, 6]);
  });



  it("Should calculate double of values", () => {
    const stubValue = [1, 2, 3];
    const fetchArrSpy = spyOn(service, "fetchArr").and.returnValue(stubValue);
    expect(service.doubleFetch()).toEqual([2, 4, 6]);
    expect(fetchArrSpy).toHaveBeenCalled();
    expect(fetchArrSpy).toHaveBeenCalledTimes(1);
  });
});
