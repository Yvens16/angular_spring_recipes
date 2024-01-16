import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {

  constructor() { }

  double(arr: number[]): number[] {
    return arr.map(el => el * 2);
  }


  doubleFetch() {
    const arr = this.fetchArr();
    return arr.map(el => el * 2);
  }

  fetchArr(): number[] {
    return [];
  }
}
