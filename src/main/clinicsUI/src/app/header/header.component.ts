
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../models/user";
import {AuthenticationService} from "../auth/authentication.service";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Input() user:User=JSON.parse(localStorage.getItem("currentUser"));
  @Output() userChange: EventEmitter<User>;
  constructor() {
    this.userChange = new EventEmitter<User>();
  }

  ngOnInit() {
    console.log(this.user.name)
  }
  logoUt() {
    console.log("In Component")
    this.user===null;
    this.userChange.emit(this.user);
  }
}
