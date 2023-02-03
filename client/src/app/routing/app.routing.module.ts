import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { PecdashboardComponent } from '../pecdashboard/pecdashboard.component';

const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'home', component: PecdashboardComponent },
    { path: '', component: LoginComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ],
    declarations: []
})
export class AppRoutingModule { }