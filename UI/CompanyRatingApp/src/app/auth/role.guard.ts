import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { jwtDecode } from 'jwt-decode';

export const roleGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const token = authService.getToken();

  if (!token) {
    router.navigate(['/login']);
    return false;
  }

  try {
    const userRole = authService.getRoleFromToken(token);

    if (userRole === 'ADMIN') {
      return true;
    } else if (userRole === 'USER') {
      return true;
    } else {
      router.navigate(['/login']);
      return false;
    }
  } catch (error) {
    router.navigate(['/login']);
    return false;
  }
};
