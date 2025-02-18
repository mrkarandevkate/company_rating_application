package com.fqts.rating.service;

import com.fqts.rating.service.model.*;

public interface RatingService {

    RatingIdResponse addRating(AddRatingRequest addRatingRequest);

    UserRatingResponse getRatingByUserId(int userId);

    CompanyRatingResponse getRatingByCompanyId(int companyId);

    CompanyAverageRatingList getAllRatings();
}
