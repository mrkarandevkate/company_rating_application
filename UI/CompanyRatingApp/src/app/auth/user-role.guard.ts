import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

export const userRoleGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const token = authService.getToken();

  if (!token) {
    router.navigate(['/login']);
    return false;
  }

  try {
    const userRole = authService.getRoleFromToken(token);

    if (userRole === 'USER') {
      return true;
    } else {
      router.navigate(['/']);
      return false;
    }
  } catch (error) {
    console.error('Error decoding token', error);
    router.navigate(['/login']);
    return false;
  }
};
