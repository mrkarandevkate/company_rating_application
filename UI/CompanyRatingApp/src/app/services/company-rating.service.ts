import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompanyRatingService {
  private allCompanyRatingUrl = 'http://localhost:8090/rating/getcompanyaveragerating';
  private companyRatingByIdUrl = 'http://localhost:8090/rating/getcompanyrating';

  constructor(private http: HttpClient) { }

  // Fetch all company ratings
  getCompanyRatings(): Observable<any> {
    return this.http.get<any>(this.allCompanyRatingUrl);
  }

  // Fetch specific company rating details by companyId
  getCompanyRatingById(companyId: number): Observable<any> {
    return this.http.get<any>(`${this.companyRatingByIdUrl}/${companyId}`);
  }

  logout(): Observable<string> {
    return of("Hello");  // Wrap the string in an Observable using 'of()'
  }
}
