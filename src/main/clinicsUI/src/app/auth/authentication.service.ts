import { Injectable } from '@angular/core';
import {Http, Headers, Response, RequestOptions} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'
import {User} from "../models/user";

@Injectable()
export class AuthenticationService {
   user:User;
  public token: string;
  private baseUrl = 'http://localhost:8080';
  constructor(private http: Http) {
    // set token if saved in local storage
    var currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  login(email: string, password: string):Observable<any> {
    let options = new RequestOptions({headers: new Headers({'Content-Type': 'application/json'})});
    return this.http.post(this.baseUrl+'/rest/auth', JSON.stringify({ email: email, password: password }),options)
      .map((response: Response) => {
      console.log(response)
        this.user=response.json();
      console.log(this.user.lastName)
        let token = response.json() && response.json().token;
        if (token) {
          // set token property
          this.token = token;

          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(this.user));
          console.log(localStorage.getItem("currentUser"));

          // return true to indicate successful login
          return true;
        } else {
          // return false to indicate failed login
          return false;
        }
      });
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');

  }
}
