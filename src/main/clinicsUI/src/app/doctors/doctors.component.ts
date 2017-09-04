import {Component, OnInit} from '@angular/core';
import {DoctorsService} from "./doctor-list/doctors.service";
import {Doctor} from "../models/doctor";
import {DoctorSearch} from "../models/doctorSearch";

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css'],
  providers: [DoctorsService]
})
export class DoctorsComponent implements OnInit {
  doctors: DoctorSearch[];
  activeDoctors: DoctorSearch[];

  constructor(private doctorService: DoctorsService) {
  }

  ngOnInit() {
    this.doctorService.getAllDoctors().subscribe((response) => {
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
        if (doctor.specialisation !== null) {
          if ((doctor.specialisation.toLowerCase().indexOf(event.name.toLowerCase()) >= 0)) {
            this.activeDoctors.push(doctor);
          }
        }
      }
    }
  }
}
