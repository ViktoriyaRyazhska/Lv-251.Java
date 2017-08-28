import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DoctorsComponent} from './doctors/doctors.component';
import {ClinicsComponent} from './clinics/clinics.component';
import {HomeComponent} from './home/home.component';
import {UserCabinetComponent} from './user-cabinet/user-cabinet.component';

const appRoutes: Routes =[
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home' , component: HomeComponent},
  {path: 'doctors' , component: DoctorsComponent},
  {path: 'personal-cabinet' , component: UserCabinetComponent},
  {path: 'clinics' , component: ClinicsComponent}
];
  @NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export  class AppRoutingModule {

}
