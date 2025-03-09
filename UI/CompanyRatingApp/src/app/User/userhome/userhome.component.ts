import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-userhome',
  imports: [CommonModule, RouterModule],
  templateUrl: './userhome.component.html',
  styleUrl: './userhome.component.css'
})
export class UserhomeComponent {
  constructor(
    private router: Router,
    private authService: AuthService,
  ) { }
  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
