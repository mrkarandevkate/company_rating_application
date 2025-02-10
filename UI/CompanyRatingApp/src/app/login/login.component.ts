import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginData = { email: '', password: '' };
  errorMessage: string = '';

  constructor(private http: HttpClient) { }

  login() {
    console.log(this.loginData)
    this.http.post('http://localhost:8095/auth/login', this.loginData)
      .subscribe({
        next: (response) => {
          console.log('Login successful', response);
          localStorage.setItem('token', (response as any).token);
        },
        error: (error) => {
          this.errorMessage = 'Invalid email or password';
          console.error('Login failed', error);
        }
      });
  }
}
