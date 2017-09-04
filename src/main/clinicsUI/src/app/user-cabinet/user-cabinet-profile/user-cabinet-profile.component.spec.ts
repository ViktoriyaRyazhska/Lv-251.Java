import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCabinetProfileComponent } from './user-cabinet-profile.component';

describe('UserCabinetProfileComponent', () => {
  let component: UserCabinetProfileComponent;
  let fixture: ComponentFixture<UserCabinetProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserCabinetProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserCabinetProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
