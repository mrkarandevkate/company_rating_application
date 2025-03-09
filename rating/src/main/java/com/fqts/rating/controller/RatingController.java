package com.fqts.rating.controller;

import com.fqts.rating.controller.model.*;
import com.fqts.rating.mapper.ControllerToServiceRatingMapper;
import com.fqts.rating.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
@Tag(name = "Rating API", description = "Operations related to rating management") // API Tag
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Operation(summary = "Add a new rating", description = "Allows a user to submit a rating for a company.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Rating added successfully"),
    })
    @PostMapping("/addrating")
    public ResponseEntity<RatingIdResponse> addRating(@RequestBody AddRatingRequest addRatingRequest) {
        com.fqts.rating.service.model.RatingIdResponse serviceResponse =
                ratingService.addRating(ControllerToServiceRatingMapper.mapServiceToControllerRating(addRatingRequest));

        RatingIdResponse ratingIdResponse =
                ControllerToServiceRatingMapper.mapServiceToControllerRatingIDResponse(serviceResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(ratingIdResponse);
    }


    @Operation(summary = "Get user ratings", description = "Fetches all ratings given by a specific user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ratings retrieved successfully"),
    })
    @GetMapping("/getratingbyuserid/{userId}")
    public UserRatingResponse getRatingGivenByUserId(
            @Parameter(description = "User ID to retrieve ratings") @PathVariable("userId") int userId) {

        com.fqts.rating.service.model.UserRatingResponse serviceUserRatingResponse =
                ratingService.getRatingByUserId(userId);

        return ControllerToServiceRatingMapper.mapServiceToControllerUserratingresponse(serviceUserRatingResponse);
    }


    @Operation(summary = "Get company ratings", description = "Retrieves all ratings received by a specific company.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company ratings retrieved successfully"),
    })
    @GetMapping("/getcompanyrating/{companyId}")
    public CompanyRatingResponse getRatingForCompany(
            @Parameter(description = "Company ID to retrieve ratings") @PathVariable("companyId") int companyId) {

        com.fqts.rating.service.model.CompanyRatingResponse serviceCompanyRatingResponse =
                ratingService.getRatingByCompanyId(companyId);

        return ControllerToServiceRatingMapper.mapServiceToControllerCompanyRatingResponse(serviceCompanyRatingResponse);
    }


    @Operation(summary = "Get average ratings for all companies", description = "Retrieves the average rating for each company.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Company average ratings retrieved successfully")
    })
    @GetMapping("/getcompanyaveragerating")
    public CompanyAverageRatingList getRatingsForAllCompanies() {
        List<com.fqts.rating.service.model.CompanyAverageRating> serviceCompanyRatingResponses =
                ratingService.getAllRatings();

        CompanyAverageRatingList companyAverageRatingList = new CompanyAverageRatingList();

        for (com.fqts.rating.service.model.CompanyAverageRating serviceCompanyRatingResponse : serviceCompanyRatingResponses) {
            CompanyAverageRating companyAverageRating =
                    ControllerToServiceRatingMapper.mapServiceToControllerCompanyAverageRating(serviceCompanyRatingResponse);
            companyAverageRatingList.add(companyAverageRating);
        }

        return companyAverageRatingList;
    }
}
