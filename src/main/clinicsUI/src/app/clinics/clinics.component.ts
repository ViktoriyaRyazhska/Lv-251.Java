import { Component, OnInit } from '@angular/core';

import {Clinic} from "../models/Clinic";
import {ClinicsService} from "./clinics.service";


@Component({
  selector: 'app-clinics',
  templateUrl: './clinics.component.html',
  styleUrls: ['./clinics.component.css'],
  providers: [ClinicsService]
})
export class ClinicsComponent implements OnInit {
 clinics: Clinic[];
  constructor(private clinicsService: ClinicsService) { }

  ngOnInit() {
    this.clinicsService.getAllClinics().subscribe((responce)=> {
      this.clinics = responce.json();
    });




  }





}
