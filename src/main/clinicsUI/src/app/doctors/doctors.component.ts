import {Component, OnInit} from '@angular/core';
import {DoctorsService} from "./doctor-list/doctors.service";
import {Doctor} from "../models/doctor";

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css'],
  providers: [DoctorsService]
})
export class DoctorsComponent implements OnInit {
  private doctors: Doctor[];
  private activeDoctors: Doctor[];

  constructor(private doctorService: DoctorsService) {
  }

  ngOnInit() {
    this.doctorService.getAllDoctors().subscribe((response) => {
      console.log(1);
      this.doctors = response.json();
      this.activeDoctors = this.doctors;
    });

  }

  onRecieveInfo(event: { select: number, name: string, district: string }) {
    this.activeDoctors = [];
    switch (event.select) {
      case (1):
        /*       for (let doctor of this.doctors) {
                 if ((doctor.district.toLowerCase().indexOf(event.distinct.toLowerCase()) >= 0)
                   && (clinic.name.toLowerCase().indexOf(event.clinicSearch.toLowerCase()) >= 0)) {
                   this.activeClinics.push(clinic);
                 }
               }*/
        break;
      default:

        break;
    }
  }
}
