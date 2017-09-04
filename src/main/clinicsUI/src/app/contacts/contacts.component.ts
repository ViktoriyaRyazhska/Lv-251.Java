import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

import {ContactService} from "./contact.service";


@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {
  contactUsForm: FormGroup;
  success: boolean;

  constructor(private contactService: ContactService) { }

  ngOnInit() {
    this.contactUsForm = new FormGroup ({
      'name': new FormControl(null, Validators.required),
      'email': new FormControl(null, [ Validators.required, Validators.email]),
      'phone': new FormControl(null, ),
      'subject': new FormControl(null, Validators.required),
      'message': new FormControl(null, Validators.required)
    })
  }

  onSubmit(){
    this.onSend();
    this.contactUsForm.reset();
  }

  onSend(){
    this.contactService.storeIt(this.contactUsForm).subscribe(
      (response) => this.success = response.ok.valueOf()
    )
  }
}
