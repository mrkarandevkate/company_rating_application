import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private apiUrl = "http://localhost:8085/company";

  constructor(private http: HttpClient) { }

  getAllCompany(): Observable<any> {
    return this.http.get(this.apiUrl + "/getallcompany");
  }

  addCompany(company: any): Observable<any> {
    return this.http.post(this.apiUrl + "/addcompany", company);
  }

  updateCompany(company: any): Observable<any> {
    return this.http.put(this.apiUrl + "/udpatecompany", company);
  }
  deleteCompany(companyId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/deletecompany/${companyId}`);
  }


}
