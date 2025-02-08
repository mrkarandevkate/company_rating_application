import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; // Import Router for navigation
import { CompanyRatingService } from '../services/company-rating.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-rating',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {
  companyRatings: any[] = [];

  constructor(
    private companyService: CompanyRatingService,
    private router: Router // Inject Router
  ) { }

  ngOnInit(): void {
    // Fetch all company ratings
    this.companyService.getCompanyRatings().subscribe((data) => {
      this.companyRatings = data;
    });
  }

  viewCompanyDetails(companyId: string): void {
    const encodedId = encodeURIComponent(companyId);
    this.router.navigate(['/company', encodedId]);
  }
}
