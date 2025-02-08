import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'app-admin-user',
  imports: [CommonModule],
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css']
})
export class AdminUserComponent implements OnInit {

  users: any[] = [];
  loading: boolean = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void {
    this.loading = true;
    this.userService.getAllUsers().subscribe(
      (response: any[]) => {
        this.users = response;  // Assigning the response directly
        this.loading = false;
      },
      (error: any) => {
        console.error('Error fetching user data', error);
        this.loading = false;
      }
    );
  }

  activateUser(userId: number): void {
    this.loading = true;
    this.userService.activateUser(userId).subscribe(
      (response: any) => {
        console.log('Response:', response); // Log response
        this.fetchUsers();
        this.loading = false;
        alert(response.message); // Display response message
      },
      (error) => {
        console.error('Error:', error);
        alert("Failed to update user status.");
        this.loading = false;
      }
    );
  }




}
