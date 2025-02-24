import { Component, OnInit } from '@angular/core';
import { CompanyRatingService } from '../../services/company-rating.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-admin-home',
  imports: [CommonModule, RouterModule],
  standalone: true,
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent implements OnInit {
  sidebarCollapsed = false;
  username: string = '';

  constructor(
    private companyRatingService: CompanyRatingService,
    private router: Router,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    this.getUserName();
  }


  toggleSidebar(): void {
    this.sidebarCollapsed = !this.sidebarCollapsed;
    const sidebar = document.querySelector('.sidebar');
    const mainContent = document.querySelector('.main-content');
    if (sidebar && mainContent) {
      sidebar.classList.toggle('collapsed', this.sidebarCollapsed);
      mainContent.classList.toggle('collapsed', this.sidebarCollapsed);
    }
  }
  getUserName(): void {
    const token = this.authService.getToken();
    if (token) {
      this.username = this.authService.getUserNameFromToken(token);
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
