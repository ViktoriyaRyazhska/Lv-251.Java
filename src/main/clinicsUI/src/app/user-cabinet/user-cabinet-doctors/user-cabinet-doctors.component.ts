import { Component, OnInit } from '@angular/core';
import {Doctor} from "../../models/doctor";

import {UserService} from "../../user.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-user-cabinet-doctors',
  templateUrl: './user-cabinet-doctors.component.html',
  styleUrls: ['./user-cabinet-doctors.component.css'],

})
export class UserCabinetDoctorsComponent implements OnInit {
  doctors: Doctor[] = [];
  user:User=JSON.parse(localStorage.getItem("currentUser"));
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getDoctorsByUser()
      .subscribe((data)=> {
        this.doctors = data;
      }, (error)=>
        console.log(error));

  }

}
