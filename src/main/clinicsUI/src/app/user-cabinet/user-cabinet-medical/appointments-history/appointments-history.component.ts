import { Component, OnInit } from '@angular/core';
import {UserService} from "../../../user.service";
import {User} from "../../../models/user";
import {Appointment} from "../../../models/appointment";

@Component({
  selector: 'app-appointments-history',
  templateUrl: './appointments-history.component.html',
  styleUrls: ['../user-cabinet-medical.component.css']
})
export class AppointmentsHistoryComponent implements OnInit {
  user:User=JSON.parse(localStorage.getItem("currentUser"));
  appointments: Appointment[] = [];
  constructor(private userService:UserService ) { }

  ngOnInit() {

    this.userService.getAppointmentsToUser() .subscribe((data)=> {
      this.appointments = data;
    }, (error)=>
      console.log(error));


  }


}
