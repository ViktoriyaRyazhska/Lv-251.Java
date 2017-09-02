import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DoctorsComponent} from './doctors/doctors.component';
import {ClinicsComponent} from './clinics/clinics.component';
import {HomeComponent} from './home/home.component';
import {UserCabinetComponent} from './user-cabinet/user-cabinet.component';
import {ContactsComponent} from "./contacts/contacts.component";
import {RegistrationComponent} from "./auth/registration/registration.component";

const appRoutes: Routes =[
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home' , component: HomeComponent},
  {path: 'doctors' , component: DoctorsComponent},
  {path: 'personal-cabinet' , component: UserCabinetComponent},
  {path: 'clinics' , component: ClinicsComponent},
  {path: 'contact', component: ContactsComponent},
  {path: 'registration', component: RegistrationComponent}
];
  @NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export  class AppRoutingModule {

}
