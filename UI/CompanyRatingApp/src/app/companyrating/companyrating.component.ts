import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CompanyRatingService } from '../services/company-rating.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-companyrating',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './companyrating.component.html',
  styleUrls: ['./companyrating.component.css']
})
export class CompanyratingComponent implements OnInit {
  companyDetails: any = {};

  constructor(
    private companyService: CompanyRatingService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    // Get company ID from the URL
    const companyId = +this.route.snapshot.paramMap.get('id')!;

    // Fetch company rating details by ID
    this.companyService.getCompanyRatingById(companyId).subscribe(
      (response) => {
        this.companyDetails = response;
      },
      (error) => {
        console.error('Error fetching company details:', error);
      }
    );
  }
}
