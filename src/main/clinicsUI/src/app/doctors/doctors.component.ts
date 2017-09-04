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
  doctors: Doctor[];
  activeDoctors: Doctor[];

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
    if (event.select == 1) {
      for (let doctor of this.doctors) {
        if ((doctor.firstname.toLowerCase().indexOf(event.name.toLowerCase()) >= 0)
          || ((doctor.lastname.toLowerCase().indexOf(event.name.toLowerCase()) >= 0))) {
          this.activeDoctors.push(doctor);
        }
      }
    }
    else {
      for (let doctor of this.doctors) {
        if (doctor.specialization !== undefined) {
          if ((doctor.specialization.toLowerCase().indexOf(event.name.toLowerCase()) >= 0)) {
            this.activeDoctors.push(doctor);
          }
        }
      }
    }
  }
}
