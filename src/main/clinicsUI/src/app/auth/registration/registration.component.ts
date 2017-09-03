import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {RegistrationService} from "./registration.service";


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  regForm: FormGroup;

  constructor(private regService : RegistrationService) { }

  ngOnInit() {
    this.regForm = new FormGroup({
      'firstname': new FormControl(null, Validators.required),
      'lastname': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, Validators.required),
      'passwordConfirm': new FormControl(null, Validators.required)
    });
  }

  onSubmit(){
    this.onSend()
    this.regForm.reset();
  }

  onSend(){
    this.regService.storeIt(this.regForm).subscribe(
      (response) => console.log(response)
    );
  }

}
