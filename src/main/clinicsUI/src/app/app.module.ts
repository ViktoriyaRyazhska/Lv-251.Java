import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { ClinicsComponent } from './clinics/clinics.component';
import { DoctorsComponent } from './doctors/doctors.component';
import { ContactsComponent } from './contacts/contacts.component';
import { UserCabinetComponent } from './user-cabinet/user-cabinet.component';
import { DoctorCabinetComponent } from './doctor-cabinet/doctor-cabinet.component';
import { ModeratorCabinetComponent } from './moderator-cabinet/moderator-cabinet.component';
import {UserCabinetProfileComponent} from './user-cabinet/user-cabinet-profile/user-cabinet-profile.component';
import {AppRoutingModule} from './app-routing-module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    ClinicsComponent,
    DoctorsComponent,
    ContactsComponent,
    UserCabinetComponent,
    DoctorCabinetComponent,
    ModeratorCabinetComponent,
    UserCabinetProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
