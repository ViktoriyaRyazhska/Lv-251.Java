import { Component, OnInit } from '@angular/core';
import {UserService} from "../../../user.service";
import {Appointment} from "../../../models/appointment";
import {User} from "../../../models/user";

@Component({
  selector: 'app-pending-appointments',
  templateUrl: './pending-appointments.component.html',
  styleUrls: ['../user-cabinet-medical.component.css']
})
export class PendingAppointmentsComponent implements OnInit {
  private appointments: Appointment[] = [];

  constructor(private userService: UserService) {
  }

  ngOnInit() {


    this.userService.getPendingAppointmentsToUser().subscribe((data) => {
      this.appointments = data;
      console.log(this.appointments)
    }, (error) =>
      console.log(error));


  }
}
