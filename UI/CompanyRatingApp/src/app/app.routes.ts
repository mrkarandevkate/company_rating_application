import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RatingComponent } from './rating/rating.component';
import { JobsComponent } from './jobs/jobs.component';
import { LoginComponent } from './login/login.component';
import { CompanyratingComponent } from './companyrating/companyrating.component';
import { AdminHomeComponent } from './Admin/admin-home/admin-home.component';
import { AdminUserComponent } from './Admin/admin-user/admin-user.component';
import { AdminCompanyComponent } from './Admin/admin-company/admin-company.component';
import { DashboardComponent } from './Admin/dashboard/dashboard.component';
import { AdminManageComponent } from './Admin/admin-manage/admin-manage.component';
import { roleGuard } from './auth/role.guard';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'rating', component: RatingComponent },
    { path: 'company/:id', component: CompanyratingComponent },
    { path: 'job', component: JobsComponent },
    { path: 'login', component: LoginComponent },


    {
        path: 'admin-home', component: AdminHomeComponent, canActivate: [roleGuard], children:
            [
                { path: '', component: DashboardComponent },
                { path: 'admin-dashboard', component: DashboardComponent },
                { path: 'admin-user', component: AdminUserComponent },
                { path: 'admin-company', component: AdminCompanyComponent },
                { path: 'admin-manage', component: AdminManageComponent }
            ]
    },

    { path: '**', redirectTo: '', pathMatch: 'full' },  // Catch-all route for invalid URLs
];
