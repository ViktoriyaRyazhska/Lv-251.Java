import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModeratorCabinetComponent } from './moderator-cabinet.component';

describe('ModeratorCabinetComponent', () => {
  let component: ModeratorCabinetComponent;
  let fixture: ComponentFixture<ModeratorCabinetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModeratorCabinetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModeratorCabinetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
