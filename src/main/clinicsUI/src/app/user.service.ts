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


  constructor(private http: Http) {
  }

  updateUser(user: User): Observable<any> {
    return this.http.post(this.baseUrl + '/api/editUser/'+user.id, user);
  }

  getAppointmentsToUser( userId: number):Observable<any>{
    return this.http.get(this.baseUrl+'/api/getAppointmentsToUser/'+userId)
      .map((response) => response.json())
      .catch((error)=>Observable.throw(error));
  }

  getDoctorsByUser( userId: number):Observable<any>{
    return this.http.get(this.baseUrl+'/api/getDoctorsToUser/'+userId)
      .map((response) => response.json())
      .catch((error)=>Observable.throw(error));
  }

  getUserByEmail(email:string):Observable<any>{
    return this.http.get(this.baseUrl+'/api/getUser/'+email)
      .map((response) => response.json())
      .catch((error)=>Observable.throw(error));
  }

}
