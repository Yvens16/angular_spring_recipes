import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { catchError, Observable, of, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HeroesService {

  constructor(private http: HttpClient) { }

  submitData(name: string): Observable<{ message: string, status: number }> {
    return this.http.post('http://localhost:3000/heroes', {
      name,
    }).pipe(
      map(() => ({ message: name, status: 200 })),
      catchError((err: HttpResponse<any>) => {
        console.log('err:', err)
        return of({ message: `Error happened sorry braaaaaaa:  ${err.status}`, status: 400 })
      }),
    )
  }

  getHeroes() {
    return this.http.get("http://localhost:3000/heroes")
      .pipe(
        map((data) => data),
        catchError((err: HttpResponse<any>) => {
          return of({ message: `Error happened sorry braaaaaaa:  ${err.status}`, status: 400 })
        })
      )
  }
}
