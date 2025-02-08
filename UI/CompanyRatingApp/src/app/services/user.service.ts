import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userapiUrl = `http://localhost:8080/user`; // URL to fetch all users
  private adminapiUrl = `http://localhost:8080/admin`; // URL to fetch all users

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(this.adminapiUrl + "/getalluser");
  }

  activateUser(userId: any): Observable<any> {
    return this.http.put(`${this.adminapiUrl}/auth/access-control/` + userId, null)
      .pipe(
        tap(response => {
          console.log('Response:', response); // Log response
        }),
        catchError((error: any) => {
          console.error('Error:', error);
          return throwError(error);
        })
      );
  }
  getAdminCount(): Observable<any> {
    return this.http.get(this.adminapiUrl + "/admincount");
  }



}
