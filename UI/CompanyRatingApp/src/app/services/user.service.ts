import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient, private authService: AuthService) { }


  private adminapiUrl = `http://localhost:8095/admin`;
  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    const headers = new HttpHeaders().set('AUTHORIZATION', `Bearer ${token}`);

    return headers;
  }
  private userUrl = "http://localhost:8095/user"

  registerUser(userData: any): Observable<any> {
    return this.http.post(this.userUrl + "/adduser", userData);
  }


  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(this.adminapiUrl + "/getalluser", { headers: this.getHeaders() });
  }

  deleteAdmin(userId: number): Observable<any[]> {
    return this.http.delete<any[]>(this.userUrl + `/delete/${userId}`, { headers: this.getHeaders() });
  }

  getAllAdmin(): Observable<any[]> {
    return this.http.get<any[]>(this.adminapiUrl + "/getalladmin", { headers: this.getHeaders() });
  }

  activateUser(userId: any): Observable<any> {
    return this.http.put(`${this.adminapiUrl}/access-control/` + userId, null, { headers: this.getHeaders() })
      .pipe(
        tap(response => {
          console.log('Response:', response);
        }),
        catchError((error: any) => {
          console.error('Error:', error);
          return throwError(error);
        })
      );
  }

  getAdminCount(): Observable<any> {
    return this.http.get(this.adminapiUrl + "/admincount", { headers: this.getHeaders() });
  }



}
