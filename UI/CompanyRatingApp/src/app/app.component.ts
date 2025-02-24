import { CommonModule } from '@angular/common';
import { Component, HostListener, OnInit } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  imports: [RouterModule, CommonModule],
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'CompanyRatingApp';
  userRole: string = '';
  currentPage: string = 'home';

  ngOnInit(): void {
    this.checkRole();
  }
  setActivePage(page: string): void {
    this.currentPage = page;
  }

  constructor(private router: Router,
    public authService: AuthService,
  ) { }


  shouldHideFooter(): boolean {
    const currentUrl = this.router.url;
    return currentUrl.startsWith('/admin-home');
  }
  checkRole() {
    const token = this.authService.getToken();
    if (token) {
      this.userRole = this.authService.getRoleFromToken(token);
    }
  }
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  navbarScrolled = false;
  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.navbarScrolled = window.scrollY > 50;
  }
}


