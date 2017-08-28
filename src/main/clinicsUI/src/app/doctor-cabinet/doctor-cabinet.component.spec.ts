import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorCabinetComponent } from './doctor-cabinet.component';

describe('DoctorCabinetComponent', () => {
  let component: DoctorCabinetComponent;
  let fixture: ComponentFixture<DoctorCabinetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorCabinetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorCabinetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
