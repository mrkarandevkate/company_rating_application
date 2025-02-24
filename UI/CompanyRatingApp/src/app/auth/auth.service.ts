import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }


  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getRoleFromToken(token: string): string {
    const decodedToken: any = jwtDecode(token);
    return decodedToken.role;
  }

  getUserNameFromToken(token: string): string {
    const decodedToken: any = jwtDecode(token);
    return decodedToken.sub;
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}
