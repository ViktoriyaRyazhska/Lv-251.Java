import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {FormGroup} from "@angular/forms";

@Injectable()
export class RegistrationService {

  constructor(private http: Http) { }

    storeIt(regForm: FormGroup) {
      return this.http.post('http://localhost:8080/api/registrationS',{
        'firstname': regForm.get('firstname').value,
        'lastname': regForm.get('lastname').value,
        'email': regForm.get('email').value,
        'password': regForm.get('password').value,
        'matchingPassword': regForm.get('passwordConfirm').value
      });
    }
}
