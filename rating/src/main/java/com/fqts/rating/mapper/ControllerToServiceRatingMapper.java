package com.fqts.rating.mapper;

import com.fqts.rating.controller.model.*;
import com.fqts.rating.helper.RoundToOneDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ControllerToServiceRatingMapper {
    public static RatingIdResponse mapServiceToControllerRatingIDResponse(com.fqts.rating.service.model.RatingIdResponse serviceResponse) {
        RatingIdResponse ratingIdResponse = new RatingIdResponse();
        return new RatingIdResponse().ratingId(serviceResponse.getRatingId());
    }

    public static com.fqts.rating.service.model.AddRatingRequest mapServiceToControllerRating(AddRatingRequest addRatingRequest) {
        com.fqts.rating.service.model.AddRatingRequest serviceRatingRequest = new com.fqts.rating.service.model.AddRatingRequest();
        serviceRatingRequest.setUserId(addRatingRequest.getUserId());
        serviceRatingRequest.setCompanyId(addRatingRequest.getCompanyId());
        serviceRatingRequest.setRating(addRatingRequest.getRating());
        serviceRatingRequest.setComments(addRatingRequest.getComments());
        return serviceRatingRequest;
    }
    public static UserRatingResponse mapServiceToControllerUserratingresponse(com.fqts.rating.service.model.UserRatingResponse serviceUserRatingResponse) {
        UserRatingResponse userratingresponse = new UserRatingResponse();
        userratingresponse.setUserId(serviceUserRatingResponse.getUserId());
        userratingresponse.setUserName(serviceUserRatingResponse.getUserName());
        userratingresponse.setEmail(serviceUserRatingResponse.getEmail());

        List<Ratings> controllerRatingsList = new ArrayList<>();
        if (serviceUserRatingResponse.getRatings() != null) {
            for (com.fqts.rating.service.model.Ratings serviceRating : serviceUserRatingResponse.getRatings()) {
                Ratings controllerRating = new Ratings();
                controllerRating.setRatingId(serviceRating.getRatingId());
                controllerRating.setRating(serviceRating.getRating());
                controllerRating.setComments(serviceRating.getComments());
                controllerRating.setCreatedAt(serviceRating.getCreatedAt());

                if (serviceRating.getCompanydetails() != null) {
                    Company company = new Company();
                    company.setCompanyId(serviceRating.getCompanydetails().getCompanyId());
                    company.setCompanyName(serviceRating.getCompanydetails().getCompanyName());
                    company.setIndustry(serviceRating.getCompanydetails().getIndustry());
                    controllerRating.setCompanydetails(company);
                }
                controllerRatingsList.add(controllerRating);
            }
        }
        userratingresponse.setRatings(controllerRatingsList);

        return userratingresponse;
    }

    // Method 2: Updating CompanyRatingResponse
    public static CompanyRatingResponse mapServiceToControllerCompanyRatingResponse(com.fqts.rating.service.model.CompanyRatingResponse serviceCompanyRatingResponse) {
        CompanyRatingResponse companyRatingResponse = new CompanyRatingResponse();
        companyRatingResponse.setCompanyId(serviceCompanyRatingResponse.getCompanyId());
        companyRatingResponse.setCompanyName(serviceCompanyRatingResponse.getCompanyName());
        companyRatingResponse.setIndustry(serviceCompanyRatingResponse.getIndustry());
        companyRatingResponse.setDescription(serviceCompanyRatingResponse.getDescription());

        // Use utility method to round rating
        companyRatingResponse.setAverageRating(RoundToOneDecimal.roundToOneDecimal(serviceCompanyRatingResponse.getAverageRating()));

        List<RatingDetails> ratingDetailsList = new ArrayList<>();
        if (serviceCompanyRatingResponse.getRatingDetails() != null) {
            for (com.fqts.rating.service.model.RatingDetails serviceRatingDetails : serviceCompanyRatingResponse.getRatingDetails()) {
                RatingDetails ratingDetails = new RatingDetails();
                ratingDetails.setRating(serviceRatingDetails.getRating());
                ratingDetails.setComments(serviceRatingDetails.getComments());
                ratingDetails.setCreatedAt(serviceRatingDetails.getCreatedAt());
                ratingDetailsList.add(ratingDetails);
            }
        }
        companyRatingResponse.setRatingDetails(ratingDetailsList);

        return companyRatingResponse;
    }

    // Method 3: Updating CompanyAverageRating
    public static CompanyAverageRating mapServiceToControllerCompanyAverageRating(com.fqts.rating.service.model.CompanyAverageRating serviceCompanyRatingResponse) {
        CompanyAverageRating companyAverageRating = new CompanyAverageRating();
        companyAverageRating.setCompanyId(serviceCompanyRatingResponse.getCompanyId());
        companyAverageRating.setCompanyName(serviceCompanyRatingResponse.getCompanyName());
        companyAverageRating.setIndustry(serviceCompanyRatingResponse.getIndustry());
        companyAverageRating.setDescription(serviceCompanyRatingResponse.getDescription());

        // Use utility method to round rating
        companyAverageRating.setAverageRating(RoundToOneDecimal.roundToOneDecimal(serviceCompanyRatingResponse.getAverageRating()));

        return companyAverageRating;
    }

}
