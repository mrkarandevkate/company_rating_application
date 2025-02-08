import { Component } from '@angular/core';
import { CompanyRatingService } from '../../services/company-rating.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  imports: [CommonModule, RouterModule],
  standalone: true,
  templateUrl: './admin-home.component.html',
  styleUrl: './admin-home.component.css'
})
export class AdminHomeComponent {
  sidebarCollapsed = false;

  constructor(
    public CompanyRatingService: CompanyRatingService,
    private router: Router
  ) { }


  toggleSidebar(): void {
    this.sidebarCollapsed = !this.sidebarCollapsed;
    const sidebar = document.querySelector('.sidebar');
    const mainContent = document.querySelector('.main-content');
    if (sidebar && mainContent) {
      sidebar.classList.toggle('collapsed', this.sidebarCollapsed);
      mainContent.classList.toggle('collapsed', this.sidebarCollapsed);
    }
  }


  // get userType(): any {
  //   if (localStorage.getItem('isAdminLoggedIn')) {
  //     return 'Admin';
  //   }

  // }

  // isLoggedIn(): boolean {
  //   return this.CompanyRatingService.isAuthenticated();
  // }

  logout(): void {
    this.CompanyRatingService.logout();
    this.router.navigate(['/login']);
  }
}
