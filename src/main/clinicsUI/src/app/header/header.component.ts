
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../models/user";
import {AuthenticationService} from "../auth/authentication.service";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user:User=JSON.parse(localStorage.getItem("currentUser"));

  constructor() {

  }

  ngOnInit() {

  }
  logout() {
    localStorage.removeItem('currentUser');
this.user===null;
  }
}
