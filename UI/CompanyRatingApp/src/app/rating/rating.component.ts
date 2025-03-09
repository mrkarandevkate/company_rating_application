import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CompanyRatingService } from '../services/company-rating.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CompanyService } from '../services/company.service';
import { AuthService } from '../auth/auth.service';
@Component({
  selector: 'app-rating',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {
  currentPage: string = 'rating';

  setActivePage(page: string): void {
    this.currentPage = page;
  }
  companyRatings: any[] = [];
  companies: any[] = [];
  filteredCompanyRatings: any[] = [];
  filterText: string = '';
  showReviewForm: boolean = false;
  selectedCompanyId: number | null = null; // Dynamic value based on user input
  rating: number | null = null; // Dynamic value based on user input
  comments: string = '';
  userProfile = {
    firstName: 'Karan',
    lastName: 'Devkate',
    photoUrl: ''
  };
  ratingCount: number = 0;
  fullStars: number = 0; // Full stars count
  halfStar: boolean = false; // Whether to show a half-star
  stars: number[] = [1, 2, 3, 4, 5]; // 5 stars for rating
  hoveredRating: number = 0;
  alertmessage: string = '';
  checkLoginMessage: string = '';

  constructor(
    private companyRatingService: CompanyRatingService,
    private companyService: CompanyService,
    private router: Router,
    public authService: AuthService
  ) { }

  ngOnInit(): void {

    this.companyRatingService.getCompanyRatings().subscribe((data) => {
      this.companyRatings = data;
      this.filteredCompanyRatings = data;
    });

    this.companyService.getAllCompany().subscribe((data) => {
      this.companies = data;
    });
    this.setStars(this.ratingCount);
  }
  toggleReviewForm(): void {
    if (!this.authService.isLoggedIn()) {
      this.alertmessage = 'You must be logged in to post a review.';
      return;
    }

    this.showReviewForm = !this.showReviewForm;
  }

  setStars(ratingCount: number): void {
    this.fullStars = Math.floor(ratingCount);
    this.halfStar = ratingCount % 1 >= 0.5;
  }

  setRating(star: number): void {
    this.ratingCount = star;
    this.setStars(star); // Set full and half stars based on the selected rating
  }

  onHover(star: number): void {
    this.hoveredRating = star; // Track the hovered star
  }

  onLeave(): void {
    this.hoveredRating = 0; // Reset when the hover state is removed
  }

  onFilterChange(): void {
    this.filteredCompanyRatings = this.companyRatings.filter(company =>
      company.companyName.toLowerCase().includes(this.filterText.toLowerCase())
    );
  }

  viewCompanyDetails(companyId: string): void {
    const encodedId = encodeURIComponent(companyId);
    this.router.navigate(['/company', encodedId]);
  }

  submitReview(): void {
    if (!this.authService.isLoggedIn()) {
      alert("You must be logged in to submit a review.");
      return;
    }
    if (this.selectedCompanyId === null || this.ratingCount === null) {
      alert("Please select a company and a rating before submitting.");
      return;
    }


    const reviewData = {
      userId: 752,
      companyId: this.selectedCompanyId,
      rating: this.ratingCount,
      comments: this.comments
    };
    this.companyRatingService.submitReview(reviewData).subscribe({
      next: (response) => {
        alert('Review submitted successfully');
        this.showReviewForm = false;
      },
      error: (err) => {
        console.error('Error submitting review', err);
      }
    });
  }


  getInitials(firstName: string, lastName: string): string {
    const firstInitial = firstName.charAt(0).toUpperCase();
    const lastInitial = lastName.charAt(0).toUpperCase();
    return `${firstInitial}${lastInitial}`;
  }
  viewDetails() {
    alert("You must be logged in to view the detailed review.");
  }


}
