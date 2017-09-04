import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DoctorsComponent} from './doctors/doctors.component';
import {ClinicsComponent} from './clinics/clinics.component';
import {HomeComponent} from './home/home.component';
import {UserCabinetComponent} from './user-cabinet/user-cabinet.component';
import {UserCabinetMedicalComponent} from './user-cabinet/user-cabinet-medical/user-cabinet-medical.component';
import {UserCabinetProfileComponent} from './user-cabinet/user-cabinet-profile/user-cabinet-profile.component';
import {UserCabinetDoctorsComponent} from "./user-cabinet/user-cabinet-doctors/user-cabinet-doctors.component";

import {AppointmentsHistoryComponent} from './user-cabinet/user-cabinet-medical/appointments-history/appointments-history.component';
import {LoginComponent} from "./auth/login/login.component";
import {ContactsComponent} from "./contacts/contacts.component";
import {RegistrationComponent} from "./auth/registration/registration.component";
import {PendingAppointmentsComponent} from "./user-cabinet/user-cabinet-medical/pending-appointments/pending-appointments.component";


const appRoutes: Routes =[
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home' , component: HomeComponent},
  {path: 'doctors' , component: DoctorsComponent},
  {path: 'login' , component: LoginComponent},
  {path: 'logout' , component: LoginComponent},
  {path: 'contact', component: ContactsComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'personal-cabinet'
    , component: UserCabinetComponent,
    children: [
      {path: '', component:UserCabinetProfileComponent },
    {path: 'profile', component:UserCabinetProfileComponent },
    {path: 'medical-info' , component: UserCabinetMedicalComponent,children: [
      {path: '', component:AppointmentsHistoryComponent },
      { path: 'appointments-history', component:AppointmentsHistoryComponent},
      { path: 'pending-appointments', component:PendingAppointmentsComponent}
    ]},

      {path: 'doctors' , component:UserCabinetDoctorsComponent}

    ]
  },
  {path: 'clinics' , component: ClinicsComponent}

];

  @NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export  class AppRoutingModule {

}
