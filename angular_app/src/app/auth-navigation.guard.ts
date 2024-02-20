import { CanActivateFn } from '@angular/router';

export const authNavigationGuard: CanActivateFn = (route, state) => {
  const accessToken = localStorage.getItem('token');
  if (!accessToken) return false;
  return true;
};
