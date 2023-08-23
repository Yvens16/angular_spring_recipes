import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
/** 
 * ng g s authentication: command poour créer le service
 * */
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }


  login(email: string, password: string) {
    return this.http.post<any>(`http://localhost:8080/login`, { email, password })
      .pipe(tap((data: any) => {
      const response = data.data;
      console.log('@@@@@@@@@response:', response)

        if (response && response.token) {
          localStorage.setItem('token', response.token);
        }
        return response;
      }));
  };

  register({ email, password, username }: { email: string, password: string, username: string }) {
    return this.http.post<any>(`http://localhost:8080/register`, { email, password, username })
      .pipe(tap((data: any) => {
        const response = data.data;
        // login successful if there's a jwt token in the response
        if (response && response.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('token', response.token);
        }
        return data;
      }))
  }

  onSignOut() {
    localStorage.removeItem('token');
  }

  getUserData() {
    return this.http.get<any>(`http://localhost:8080/user-admin-data`)
      .pipe((data: any) => {
        return data;
      });
  }

  getAdminOnlyData() {
    return this.http.get<any>(`http://localhost:8080/only-admin-data`)
      .pipe((data: any) => {
        console.log('data:', data)
        return data;
      }
      );
  }
}
