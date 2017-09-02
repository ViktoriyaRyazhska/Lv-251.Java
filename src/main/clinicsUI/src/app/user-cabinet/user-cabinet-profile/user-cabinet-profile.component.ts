import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import {UserService} from '../../user.service';

import {error} from "util";
import {User} from "../../models/user";

@Component({
  selector: 'app-user-cabinet-profile',
  templateUrl: './user-cabinet-profile.component.html',
  styleUrls: ['./user-cabinet-profile.component.css']
})
export class UserCabinetProfileComponent implements OnInit {
  editUserForm: FormGroup;

  userUpdate = new User;
  user:User=JSON.parse(localStorage.getItem("currentUser"));

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.editUserForm = new FormGroup({
      'name': new FormControl('nnnn',Validators.required),
      'lastName': new FormControl('',Validators.required),
      'email': new FormControl('',[Validators.required,Validators.email]),
      'city': new FormControl(''),
      'district': new FormControl(''),
      'address': new FormControl(''),
    })
this.userService.getUserByEmail(this.user.email).subscribe(

  (response)=> {
    this.userUpdate=response;
    this.editUserForm = new FormGroup({
      'name': new FormControl(this.userUpdate.name,Validators.required),
      'lastName': new FormControl(this.userUpdate.lastName,Validators.required),
      'email': new FormControl(this.userUpdate.email,[Validators.required,Validators.email]),
      'city': new FormControl(this.userUpdate.city),
      'district': new FormControl(this.userUpdate.district),
      'address': new FormControl(this.userUpdate.address),
    })
    console.log(response)
  },
  (error)=> console.error(error)
)


  }
  onSave(){
    this.userUpdate= this.editUserForm.value;
    this.userUpdate.id =this.user.id;
    this.userService.updateUser(this.userUpdate).subscribe(
      (response)=> console.log(response),
      (error)=> console.error(error)
    )


  }

}
