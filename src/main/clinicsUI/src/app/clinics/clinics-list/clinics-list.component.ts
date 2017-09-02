import {Component, Input, OnInit} from '@angular/core';

import {Clinic} from "../../models/Clinic";

@Component({
  selector: 'app-clinics-list',
  templateUrl: './clinics-list.component.html',
  styleUrls: ['./clinics-list.component.css']
})
export class ClinicsListComponent implements OnInit {
  @Input() clinic : Clinic;
  constructor() { }

  ngOnInit() {
  }

}
