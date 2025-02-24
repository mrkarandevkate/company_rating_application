import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-manage',
  standalone: true, // This allows using 'CommonModule' in a standalone component
  imports: [CommonModule],
  templateUrl: './admin-manage.component.html',
  styleUrls: ['./admin-manage.component.css'] // Fix to 'styleUrls' instead of 'styleUrl'
})
export class AdminManageComponent implements OnInit {
  users: any[] = [];
  loading: boolean = false;
  showAdminForm: boolean = false;
  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void {
    this.loading = true;
    this.userService.getAllAdmin().subscribe(
      (response: any[]) => {
        this.users = response;
        this.loading = false;
      },
      (error: any) => {
        console.error('Error fetching user data', error);
        this.loading = false;
      }
    );
  }

  deleteAdmin(userId: number): void {
    this.userService.deleteAdmin(userId).subscribe(
      (response: any[]) => {
        alert("Admin Deleted Successfully");
        this.fetchUsers(); // Re-fetch the users to update the UI after deletion
      },
      (error: any) => {
        console.error('Error deleting admin', error);
        this.loading = false;
      }
    );
  }
}
