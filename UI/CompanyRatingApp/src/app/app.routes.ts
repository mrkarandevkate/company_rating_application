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
import { redirectGuard } from './auth/redirect.guard';
import { UserratingComponent } from './User/userrating/userrating.component';
import { userRoleGuard } from './auth/user-role.guard';
import { UserhomeComponent } from './User/userhome/userhome.component';

export const routes: Routes = [
    { path: '', component: HomeComponent, canActivate: [redirectGuard] },
    { path: 'rating', component: RatingComponent, canActivate: [redirectGuard] },
    { path: 'company/:id', component: CompanyratingComponent, canActivate: [redirectGuard] },
    { path: 'job', component: JobsComponent, canActivate: [redirectGuard] },
    { path: 'login', component: LoginComponent, canActivate: [redirectGuard] },
    {
        path: 'user', component: UserhomeComponent, canActivate: [userRoleGuard], children: [
            { path: '', component: UserratingComponent }
        ]
    },
    {
        path: 'admin', component: AdminHomeComponent, canActivate: [roleGuard], children:
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
