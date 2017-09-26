import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

import {Clinic} from "../models/Clinic";
import {ClinicsService} from "./clinics.service";
import {distinct} from "rxjs/operator/distinct";


@Component({
  selector: 'app-clinics',
  templateUrl: './clinics.component.html',
  styleUrls: ['./clinics.component.css'],
  providers: [ClinicsService]
})
export class ClinicsComponent implements OnInit {
  @ViewChild('map') map : ElementRef;
  clinics: Clinic[];
  activeClinics: Clinic[];

  constructor(private clinicsService: ClinicsService) {
  }

  ngOnInit() {
    this.clinicsService.getAllClinics().subscribe((responce) => {
      this.clinics = responce.json();
      this.activeClinics = this.clinics;
    });
  }

  OnSubmitChange(event: { distinct: string, clinicSearch: string }) {
    this.activeClinics = [];
    for (let clinic of this.clinics) {
      if ((clinic.district_name.toLowerCase().indexOf(event.distinct.toLowerCase()) >= 0)
        && (clinic.name.toLowerCase().indexOf(event.clinicSearch.toLowerCase()) >= 0)) {
        this.activeClinics.push(clinic);
      }
    }
  }


}
