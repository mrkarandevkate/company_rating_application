import { TestBed } from '@angular/core/testing';

import { CompanyRatingService } from './company-rating.service';

describe('CompanyRatingService', () => {
  let service: CompanyRatingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompanyRatingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
