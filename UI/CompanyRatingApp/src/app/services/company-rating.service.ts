import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class CompanyRatingService {
  private allCompanyRatingUrl = 'http://localhost:8095/rating/getcompanyaveragerating';
  private companyRatingByIdUrl = 'http://localhost:8095/rating/getcompanyrating';
  private submitCompanyRatingUrl = 'http://localhost:8095/rating/addrating'

  constructor(private http: HttpClient, private authService: AuthService) { }
  private getHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }
  getCompanyRatings(): Observable<any> {
    return this.http.get<any>(this.allCompanyRatingUrl);
  }

  getCompanyRatingById(companyId: number): Observable<any> {
    return this.http.get<any>(`${this.companyRatingByIdUrl}/${companyId}`, { headers: this.getHeaders() });
  }

  logout(): Observable<string> {
    return of("Hello");
  }

  // Submit a review
  submitReview(company: any): Observable<any> {
    return this.http.post<any>(this.submitCompanyRatingUrl, company, { headers: this.getHeaders() });
  }
}
