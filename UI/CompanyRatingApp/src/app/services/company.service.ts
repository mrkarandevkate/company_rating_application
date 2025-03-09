import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private apiUrl = "http://localhost:8095/company";

  constructor(private http: HttpClient, private authService: AuthService) { }

  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  getAllCompany(): Observable<any> {
    return this.http.get(this.apiUrl + "/getallcompany");
  }

  addCompany(company: any): Observable<any> {
    return this.http.post(this.apiUrl + "/addcompany", company, { headers: this.getHeaders() });
  }

  updateCompany(company: any): Observable<any> {
    return this.http.put(this.apiUrl + "/updatecompany", company, { headers: this.getHeaders() });
  }
  deleteCompany(companyId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/deletecompany/${companyId}`, { headers: this.getHeaders() });
  }


}
