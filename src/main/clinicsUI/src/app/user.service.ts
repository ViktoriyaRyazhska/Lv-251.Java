import 'rxjs/add/operator/toPromise';
import {Http, RequestOptions} from '@angular/http';
import {Injectable} from '@angular/core';

import  {Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {User} from "./models/user";

@Injectable()
export  class UserService {
  private baseUrl = 'http://localhost:8080';
  currentUser = JSON.parse(localStorage.getItem('currentUser'));
  token= "";
  constructor(private http: Http) {
  }

  updateUser(user: User): Observable<any> {
    return this.http.post(this.baseUrl + '/rest/api/editUser/' + "?token="+this.jwt(), user);
  }

  getAppointmentsToUser(): Observable<any> {
    return this.http.get(this.baseUrl + '/rest/api/getAppointmentsToUser/'  + "?token="+this.jwt())
      .map((response) => response.json())
      .catch((error) => Observable.throw(error));
  }


  getPendingAppointmentsToUser(): Observable<any> {
    return this.http.get(this.baseUrl + '/rest/api/getPendingAppointmentsToUser/'  + "?token="+this.jwt())
      .map((response) => response.json())
      .catch((error) => Observable.throw(error));
  }
  getDoctorsByUser(): Observable<any> {
    return this.http.get(this.baseUrl + '/rest/api/getDoctorsToUser/' + "?token="+this.jwt())
      .map((response) => response.json())
      .catch((error) => Observable.throw(error));
  }

  getUserByEmail(): Observable<any> {
    return this.http.get(this.baseUrl + '/rest/api/getUser/' + "?token="+this.jwt())
      .map((response) => response.json())
      .catch((error) => Observable.throw(error));
  }



  private jwt() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser !==null) {
     return this.token=currentUser.token;

    }else{
      return this.token
    }

  }
}
