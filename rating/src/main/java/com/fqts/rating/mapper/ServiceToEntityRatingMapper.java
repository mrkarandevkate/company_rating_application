package com.fqts.rating.mapper;

import com.fqts.rating.entity.Rating;
import com.fqts.rating.helper.RoundToOneDecimal;
import com.fqts.rating.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceToEntityRatingMapper {
    @Autowired
    private RestTemplate restTemplate;

    public com.fqts.rating.entity.Rating mapEntitytoServiceRating(AddRatingRequest addRatingRequest) {
        com.fqts.rating.entity.Rating entityRating = new  com.fqts.rating.entity.Rating();
        entityRating.setUserId(addRatingRequest.getUserId());
        entityRating.setCompanyId(addRatingRequest.getCompanyId());
        entityRating.setRating(addRatingRequest.getRating());
        entityRating.setComment(addRatingRequest.getComments());
        return entityRating;
    }

    public UserRatingResponse mapEntityToServiceUserRatingResponse(List<Rating> savedRatings) {
        UserRatingResponse userRatingResponse = new UserRatingResponse();
        Rating firstRating = savedRatings.get(0);
        String userUrl = "http://USER/user/getuser/" + firstRating.getUserId();
        com.fqts.rating.entity.User entityUser = restTemplate.getForObject(userUrl, com.fqts.rating.entity.User.class);

        if (entityUser != null) {
            userRatingResponse.setUserId(entityUser.getUserId());
            userRatingResponse.setUserName(entityUser.getName());
            userRatingResponse.setEmail(entityUser.getEmail());
        }
        List<Ratings> serviceRatingsList = new ArrayList<>();
        for (Rating savedRating : savedRatings) {
            Ratings ratings = new Ratings();
            ratings.setRatingId(savedRating.getRatingId());
            ratings.setRating(savedRating.getRating());
            ratings.setComments(savedRating.getComment());
            ratings.setCreatedAt(savedRating.getCreated_At());

            String companyUrl = "http://COMPANY/company/getcompany/" + savedRating.getCompanyId();
            com.fqts.rating.entity.Company entityCompany = restTemplate.getForObject(companyUrl, com.fqts.rating.entity.Company.class);

            if (entityCompany != null) {
                Company company = new Company();
                company.setCompanyId(entityCompany.getCompanyId());
                company.setCompanyName(entityCompany.getCompanyName());
                company.setIndustry(entityCompany.getIndustry());
                company.setDescription(entityCompany.getDescription());
                ratings.setCompanydetails(company);
            }

            serviceRatingsList.add(ratings);
        }
        userRatingResponse.setRatings(serviceRatingsList);
        return userRatingResponse;
    }
    public CompanyRatingResponse mapEntitytoServiceCompanyRating(List<Rating> ratingList) {
        CompanyRatingResponse companyRatingResponse = new CompanyRatingResponse();
        Rating firstRating = ratingList.get(0);
        String companyUrl = "http://COMPANY/company/getcompany/" + firstRating.getCompanyId();
        com.fqts.rating.entity.Company entityCompany = restTemplate.getForObject(companyUrl, com.fqts.rating.entity.Company.class);
        companyRatingResponse.setCompanyId(entityCompany.getCompanyId());
        companyRatingResponse.setCompanyName(entityCompany.getCompanyName());
        companyRatingResponse.setIndustry(entityCompany.getIndustry());
        companyRatingResponse.setDescription(entityCompany.getDescription());
        float totalRating=0;
        int ratingCount=ratingList.size();
        for(Rating rating:ratingList) {
            totalRating += rating.getRating();
        }

        float averageRating = 0;
        if (!ratingList.isEmpty()) {
            averageRating = (float) totalRating /ratingCount;
        }
        companyRatingResponse.setAverageRating(RoundToOneDecimal.roundToOneDecimal(averageRating));
        List<RatingDetails> servieRatingList= new ArrayList<>();
        for (Rating fetchedRating : ratingList) {
            RatingDetails ratingDetails = new RatingDetails();
            ratingDetails.setRatingId(fetchedRating.getRatingId());
            ratingDetails.setRating(fetchedRating.getRating());
            ratingDetails.setComments(fetchedRating.getComment());
            ratingDetails.setCreatedAt(fetchedRating.getCreated_At());
            servieRatingList.add(ratingDetails);
        }

        companyRatingResponse.setRatingDetails(servieRatingList);

        return companyRatingResponse;
    }
    public CompanyAverageRating mapToCompanyRatingResponse(List<Rating> ratingList) {
        CompanyAverageRating companyRatingResponse = new CompanyAverageRating();
        Rating firstRating = ratingList.get(0);

        String companyUrl = "http://COMPANY/company/getcompany/" + firstRating.getCompanyId();
        com.fqts.rating.entity.Company entityCompany = restTemplate.getForObject(companyUrl, com.fqts.rating.entity.Company.class);
        companyRatingResponse.setCompanyId(entityCompany.getCompanyId());
        companyRatingResponse.setCompanyName(entityCompany.getCompanyName());
        companyRatingResponse.setIndustry(entityCompany.getIndustry());
        companyRatingResponse.setDescription(entityCompany.getDescription());
        // Calculate the average rating
        float totalRating = 0;
        int ratingCount = ratingList.size();
        for (Rating rating : ratingList) {
            totalRating += rating.getRating();
        }

        float averageRating = 0;
        if (ratingCount > 0) {
            averageRating = totalRating / ratingCount;
        }
        companyRatingResponse.setAverageRating(RoundToOneDecimal.roundToOneDecimal(averageRating));
        return companyRatingResponse;
    }

}
