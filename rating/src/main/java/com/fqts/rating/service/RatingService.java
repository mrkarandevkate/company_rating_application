package com.fqts.rating.service;

import com.fqts.rating.entity.Rating;
import com.fqts.rating.mapper.ServiceToEntityRatingMapper;
import com.fqts.rating.repository.RatingRepository;
import com.fqts.rating.service.model.*;
import com.fqts.rating.exception.RatingNotFoundException; // Import the new exception
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ServiceToEntityRatingMapper serviceToEntityRatingMapper;

    // Method to add a rating
    public RatingIdResponse addRating(AddRatingRequest addRatingRequest) {
        try {
            com.fqts.rating.entity.Rating savedRating = ratingRepository.save(serviceToEntityRatingMapper.mapEntitytoServiceRating(addRatingRequest));
            return new RatingIdResponse().ratingId(savedRating.getRating());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while adding the rating", e);
        }
    }

    // Method to get ratings by userId
    public UserRatingResponse getRatingByUserId(int userId) {
        try {
            List<Rating> savedRatings = ratingRepository.findByUserId(userId);

            if (savedRatings.isEmpty()) {
                throw new RatingNotFoundException("No ratings found for user with ID: " + userId); // Throw RatingNotFoundException
            }

            return serviceToEntityRatingMapper.mapEntityToServiceUserRatingResponse(savedRatings);
        } catch (RatingNotFoundException e) {
            throw e; // Rethrow the custom exception if no ratings found
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching ratings for user with ID: " + userId, e);
        }
    }

    // Method to get ratings by companyId
    public CompanyRatingResponse getRatingByCompanyId(int companyId) {
        try {
            List<Rating> ratingList = ratingRepository.findByCompanyId(companyId);

            if (ratingList.isEmpty()) {
                throw new RatingNotFoundException("No ratings found for company with ID: " + companyId); // Throw RatingNotFoundException
            }

            return serviceToEntityRatingMapper.mapEntitytoServiceCompanyRating(ratingList);
        } catch (RatingNotFoundException e) {
            throw e; // Rethrow the custom exception if no ratings found
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching ratings for company with ID: " + companyId, e);
        }
    }

    // Method to get all ratings grouped by company
    public CompanyAverageRatingList getAllRatings() {
        try {
            List<Rating> allRatings = ratingRepository.findAll();
            if (allRatings.isEmpty()) {
                throw new RatingNotFoundException("No ratings available"); // Throw RatingNotFoundException
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
