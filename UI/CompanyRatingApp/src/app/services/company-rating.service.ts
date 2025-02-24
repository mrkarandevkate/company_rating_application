import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompanyRatingService {
  private allCompanyRatingUrl = 'http://localhost:8090/rating/getcompanyaveragerating';
  private companyRatingByIdUrl = 'http://localhost:8090/rating/getcompanyrating';
  private submitCompanyRatingUrl = 'http://localhost:8090/rating/addrating'

  constructor(private http: HttpClient) { }

  getCompanyRatings(): Observable<any> {
    return this.http.get<any>(this.allCompanyRatingUrl);
  }

  getCompanyRatingById(companyId: number): Observable<any> {
    return this.http.get<any>(`${this.companyRatingByIdUrl}/${companyId}`);
  }

  logout(): Observable<string> {
    return of("Hello");
  }

  // Submit a review
  submitReview(company: any): Observable<any> {
    return this.http.post<any>(this.submitCompanyRatingUrl, company);
  }
}
