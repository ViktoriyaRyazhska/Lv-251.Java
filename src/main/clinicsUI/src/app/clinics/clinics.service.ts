import {Injectable} from "@angular/core";
import {Http} from "@angular/http";

@Injectable()
export class ClinicsService{
  constructor(private http: Http){}
  getAllClinics() {
    return this.http.get("http://localhost:8080/api/getAllClinics");
  }
}
