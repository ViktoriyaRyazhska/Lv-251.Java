import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';

@Component({
  selector: 'app-doctor-edit',
  templateUrl: './doctor-edit.component.html',
  styleUrls: ['./doctor-edit.component.css']
})
export class DoctorEditComponent implements OnInit {
  @ViewChild('district') district: ElementRef;
  @ViewChild('name') name: ElementRef;
  @ViewChild('select') select: ElementRef;
  @Output() doctorDetails = new EventEmitter<{select:number,name : string, district: string}>();
  constructor() { }

  ngOnInit() {
  }

  onSmthChange(){
    this.doctorDetails.emit({select: this.select.nativeElement.value,
      name : this.name.nativeElement.value,
      district: this.district.nativeElement.value});
  }

  onSelect(){
    this.name.nativeElement.value = "";
  }
}
