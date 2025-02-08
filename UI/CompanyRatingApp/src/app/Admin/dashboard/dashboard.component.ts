import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UserService } from '../../services/user.service';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-dashboard',
  imports: [RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  userCount: number = 0; // Total users
  companyCount: number = 0;
  adminCount: number = 0;
  awaitingApprovalCount: number = 0; // Users awaiting approval

  constructor(private userService: UserService
    , private companyService: CompanyService
  ) { }

  ngOnInit(): void {
    this.fetchUserData();
    this.fetchCompanyData();
    this.fetchAdminCount();
  }

  fetchAdminCount(): void {
    this.userService.getAdminCount().subscribe(
      (admin) => {
        this.adminCount = admin.adminCount;
      },
      (error) => {
        console.error('Error fetching users:', error);
      }
    )
  }

  fetchUserData(): void {
    this.userService.getAllUsers().subscribe(
      (users) => {
        this.userCount = users.length;
        this.awaitingApprovalCount = users.filter(user => user.status === 'NOTALLOWED').length;
      },
      (error) => {
        console.error('Error fetching users:', error);
      }
    );
  }
  fetchCompanyData(): void {
    this.companyService.getAllCompany().subscribe(
      (company) => {
        this.companyCount = company.length;
      },
      (error) => {
        console.error('Error fetching user data', error);

      }
    );
  }
} 
