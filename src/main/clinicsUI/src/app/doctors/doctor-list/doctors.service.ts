import {Injectable} from "@angular/core";
import {Http} from "@angular/http";


@Injectable()
export class DoctorsService{
  constructor(private http: Http){}
  getAllDoctors() {
    return this.http.get("http://localhost:8080/api/getAllDoctors");
  }
}
