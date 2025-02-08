import { CompanyService } from '../../services/company.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-company',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-company.component.html',
  styleUrl: './admin-company.component.css'
})
export class AdminCompanyComponent implements OnInit {
  isAddingCompany: boolean = false;
  isUpdatingCompany: boolean = false;
  company: any[] = [];
  loading: boolean = false;

  newCompany = {
    companyId: 0,  // Added companyId for update
    companyName: '',
    industry: '',
    description: ''
  };

  constructor(private companyService: CompanyService) { }

  ngOnInit(): void {
    this.fetchCompany();
  }

  fetchCompany(): void {
    this.loading = true;
    this.companyService.getAllCompany().subscribe(
      (response: any[]) => {
        this.company = response;
        this.loading = false;
      },
      (error: any) => {
        console.error('Error fetching company data', error);
        this.loading = false;
      }
    );
  }

  resetForm(): void {
    this.newCompany = {
      companyId: 0,  // Reset companyId to null for new entries
      companyName: '',
      industry: '',
      description: ''
    };
  }

  addCompany(): void {
    this.loading = true;
    this.companyService.addCompany(this.newCompany).subscribe(
      (response: any) => {
        this.loading = false;
        alert("Company Added Successfully");
        this.isAddingCompany = false;
        this.resetForm();
        this.fetchCompany();
      },
      (error) => {
        this.loading = false;
        alert("Failed to add company");
      }
    );
  }

  editCompany(company: any): void {
    this.isAddingCompany = true;
    this.isUpdatingCompany = true;


    this.newCompany = { ...company };
  }

  updateCompany(): void {
    if (!this.newCompany.companyId) {
      alert("Invalid company ID for update!");
      return;
    }
    this.loading = true;
    this.companyService.updateCompany(this.newCompany).subscribe(
      (response: any) => {
        this.loading = false;
        alert("Company Updated Successfully");
        this.isAddingCompany = false;
        this.isUpdatingCompany = false;
        this.resetForm();
        this.fetchCompany();
      },
      (error) => {
        this.loading = false;
        alert("Failed to update company");
      }
    );
  }

  deleteCompany(companyId: number): void {
    this.loading = true;
    this.companyService.deleteCompany(companyId).subscribe(
      response => {
        alert("Company Deleted Successfully");
        this.fetchCompany();
      },
      error => {
        alert("Failed to delete company");
      }
    );

  }
}
