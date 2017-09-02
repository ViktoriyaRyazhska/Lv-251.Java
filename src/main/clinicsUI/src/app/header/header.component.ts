
import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {User} from "../models/user";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user:User=JSON.parse(localStorage.getItem("currentUser"));

  constructor() { }

  ngOnInit() {
    console.log(this.user.name)
  }

}
