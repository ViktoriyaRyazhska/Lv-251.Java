import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';

@Component({
  selector: 'app-clinics-edit',
  templateUrl: './clinics-edit.component.html',
  styleUrls: ['./clinics-edit.component.css']
})
export class ClinicsEditComponent implements OnInit {
  @Output() submit = new EventEmitter<{distinct : string, clinicSearch : string}>();
  @ViewChild('distinct') distinct: ElementRef;
  @ViewChild('clinic_search') clinicSearch: ElementRef;
  constructor() { }

  ngOnInit() {
  }


  onClinicSearchOrDistinctChange(){
    this.submit.emit({distinct : this.distinct.nativeElement.value,
      clinicSearch : this.clinicSearch.nativeElement.value});
  }
}
