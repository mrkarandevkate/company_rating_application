package com.fqts.rating.service;

import com.fqts.rating.entity.Rating;
import com.fqts.rating.exception.NotNullException;
import com.fqts.rating.exception.RatingNotFoundException;
import com.fqts.rating.mapper.ServiceToEntityRatingMapper;
import com.fqts.rating.repository.RatingRepository;
import com.fqts.rating.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RatingServiceImp implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ServiceToEntityRatingMapper serviceToEntityRatingMapper;

    public RatingIdResponse addRating(AddRatingRequest addRatingRequest) {
        try {
            if (addRatingRequest.getUserId() == null || addRatingRequest.getCompanyId() == null || addRatingRequest.getRating() == null) {
                throw new NotNullException("User ID, Company ID or Rating Value cannot be null");
            }
            Rating savedRating = ratingRepository.save(serviceToEntityRatingMapper.mapEntitytoServiceRating(addRatingRequest));
            return new RatingIdResponse().ratingId(savedRating.getRating());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while adding the rating", e);
        }
    }

    public UserRatingResponse getRatingByUserId(int userId) {
        try {
            List<Rating> savedRatings = ratingRepository.findByUserId(userId);
            if (savedRatings.isEmpty()) {
                throw new RatingNotFoundException("No ratings found for user with ID: " + userId);
            }
            return serviceToEntityRatingMapper.mapEntityToServiceUserRatingResponse(savedRatings);
        } catch (RatingNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching ratings for user with ID: " + userId, e);
        }
    }

    public CompanyRatingResponse getRatingByCompanyId(int companyId) {
        try {
            List<Rating> ratingList = ratingRepository.findByCompanyId(companyId);
            if (ratingList.isEmpty()) {
                throw new RatingNotFoundException("No ratings found for company with ID: " + companyId); // Throw RatingNotFoundException
            }
            return serviceToEntityRatingMapper.mapEntitytoServiceCompanyRating(ratingList);
        } catch (RatingNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching ratings for company with ID: " + companyId, e);
        }
    }

    public CompanyAverageRatingList getAllRatings() {
        try {
            List<Rating> allRatings = ratingRepository.findAll();
            if (allRatings.isEmpty()) {
                throw new RatingNotFoundException("No ratings available");
            }
            Map<Integer, List<Rating>> ratingsGroupedByCompany = allRatings.stream()
                    .collect(Collectors.groupingBy(Rating::getCompanyId));
            CompanyAverageRatingList companyRatingResponses = new CompanyAverageRatingList();
            for (Map.Entry<Integer, List<Rating>> entry : ratingsGroupedByCompany.entrySet()) {
                List<Rating> ratingsForCompany = entry.getValue();
                CompanyAverageRating companyRatingResponse = serviceToEntityRatingMapper.mapToCompanyRatingResponse(ratingsForCompany);
                companyRatingResponses.add(companyRatingResponse);
            }
            return companyRatingResponses;
        } catch (RatingNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching all ratings", e);
        }
    }
}
