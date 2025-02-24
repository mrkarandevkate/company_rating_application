import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginData = { email: '', password: '' }; // Your login data model
  errorMessage: string = '';

  constructor(private http: HttpClient, private authService: AuthService, private router: Router) { }


  login() {
    console.log(this.loginData);
    this.http.post<any>('http://localhost:8095/auth/login', this.loginData)
      .subscribe({
        next: (response) => {
          const token = response.token;
          if (token) {
            this.authService.setToken(token);
            console.log('Login successful', response);
            try {
              const decodedToken: any = jwtDecode(token);
              const userRole = decodedToken.role;
              if (userRole === 'ADMIN') {
                this.router.navigate(['/admin-home']);
              } else {
                this.router.navigate(['/home']);
              }
            } catch (error) {
              console.error('Error decoding token', error);
            }
          } else {
            this.errorMessage = 'Token not received from server';
            console.error('Login failed, no token in response', response);
          }
        },
        error: (error) => {
          this.errorMessage = 'Invalid email or password';
          console.error('Login failed', error);
        }
      });
  }
}
