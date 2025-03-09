import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

export const redirectGuard: CanActivateFn = (route, state) => {
  //this redirect guard is used restrict admin to see access link after login
  const authService = inject(AuthService);
  const router = inject(Router);

  const token = authService.getToken();

  if (token) {
    try {
      const userRole = authService.getRoleFromToken(token);

      if (userRole === 'ADMIN') {
        router.navigate(['/admin']);
        return false;
      }
    } catch (error) {
      console.error('Error decoding token', error);
      router.navigate(['/login']);
      return false;
    }
  }

  return true;
};
