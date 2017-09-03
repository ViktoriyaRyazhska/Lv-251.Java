import { Component, OnInit } from '@angular/core';
import {UserService} from "../../../user.service";
import {User} from "../../../models/user";
import {Appointment} from "../../../models/appointment";

@Component({
  selector: 'app-appointments-history',
  templateUrl: './appointments-history.component.html',
  styleUrls: ['./appointments-history.component.css']
})
export class AppointmentsHistoryComponent implements OnInit {
  user:User=JSON.parse(localStorage.getItem("currentUser"));
  private appointments: Appointment[] = [];
  constructor(private userService:UserService ) { }

  ngOnInit() {
    console.log("id"+this.user.id);
    this.userService.getAppointmentsToUser(this.user.id) .subscribe((data)=> {
      console.log(data.length);
      this.appointments = data;
      console.log(this.appointments)
    }, (error)=>
      console.log(error));


  }

}
