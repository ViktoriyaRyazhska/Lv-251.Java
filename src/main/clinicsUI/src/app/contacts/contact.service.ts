
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {FormGroup} from "@angular/forms";



@Injectable()
export class ContactService {

  constructor(private http: Http){}

  storeIt(contactUsForm: FormGroup){
    return this.http.post('http://localhost:8080/api/contact-us', {
      'name': contactUsForm.get('name').value,
      'email': contactUsForm.get('email').value,
      'phone': contactUsForm.get('phone').value,
      'subject': contactUsForm.get('subject').value,
      'message': contactUsForm.get('message').value
    });
  }
}
