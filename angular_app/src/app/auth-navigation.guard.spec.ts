import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authNavigationGuard } from './auth-navigation.guard';

describe('authNavigationGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => authNavigationGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
