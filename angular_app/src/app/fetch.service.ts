import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FetchService {

  constructor(private http: HttpClient) { }


  getData() {
    return this.http.get("https://thesimpsonsquoteapi.glitch.me/quotes")
      .pipe(
        tap(_ => console.log("Heroes are fetching")),
        catchError(err => {
          return throwError(() => err);
        })
      )
  }
}
