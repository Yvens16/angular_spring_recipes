import { Injectable } from '@angular/core';
import { CalculatorService } from './calculator.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private calculatorService: CalculatorService) { }

  getFinalValue(): number {
    const resultArr = this.calculatorService.double([]);
    return resultArr.reduce((a, c) => a + c, 0);
  }
}
