import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth/auth.service';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private userService: UserService, private http: HttpClient, private authService: AuthService, private router: Router) { }

  currentPage: string = 'login';

  setActivePage(page: string): void {
    this.currentPage = page;
  }
  errorMessage: string = '';
  showPassword = false;
  isFlipped = false;
  loginData = {
    email: '',
    password: ''
  };

  registerData = {
    name: '',
    email: '',
    password: ''
  };
  toggleForm() {
    this.isFlipped = !this.isFlipped;
  }
  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
  login() {
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
              let redirectUrl = userRole === 'ADMIN' ? '/admin-home' : '/';

              this.router.navigate([redirectUrl]).then(() => {
                window.location.reload();
              });

            } catch (error) {
              console.error('Error decoding token', error);
            }
          } else {
            this.errorMessage = 'Token not received from server';
          }
        },
        error: () => {
          this.errorMessage = 'Invalid email or password';
        }
      });
  }
  register() {
    this.userService.registerUser(this.registerData).subscribe({
      next: (response) => {
        alert("Registration successful!, check your email before login")
        this.registerData = { name: '', email: '', password: '' };
      },
      error: (error) => {
        this.errorMessage = 'Registration failed!';
      }
    });
  }
}
