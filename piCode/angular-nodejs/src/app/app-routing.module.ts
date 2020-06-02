import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PostCreateComponent } from './posts/post-create/post-create.component';

import { AuthComponent } from './auth/auth.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/auth', pathMatch: 'full' },
  {
    path: 'products',
    component: PostCreateComponent
  },
  { path: 'auth', component: AuthComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
