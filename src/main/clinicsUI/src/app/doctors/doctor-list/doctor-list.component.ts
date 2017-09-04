import {Component, Input, OnInit} from '@angular/core';
import {DoctorSearch} from "../../models/doctorSearch";

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {
  @Input() doctor : DoctorSearch;
  constructor() { }

  ngOnInit() {
  }

}
