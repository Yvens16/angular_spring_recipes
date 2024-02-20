import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PublicComponent } from './public/public/public.component';
import { ProtectedComponent } from './protected/protected/protected.component';
import { authNavigationGuard } from './auth-navigation.guard';

const routes: Routes = [
  { path: 'public', component: PublicComponent },
  { path: 'protected', component: ProtectedComponent, canActivate: [authNavigationGuard], }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
