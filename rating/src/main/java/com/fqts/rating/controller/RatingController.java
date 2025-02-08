package com.fqts.rating.controller;

import com.fqts.rating.controller.model.*;
import com.fqts.rating.mapper.ControllerToServiceRatingMapper;
import com.fqts.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping("/addrating")
    public ResponseEntity<RatingIdResponse> addRating(@RequestBody AddRatingRequest addRatingRequest){
        com.fqts.rating.service.model.RatingIdResponse serviceResponse=ratingService.addRating(ControllerToServiceRatingMapper.mapServiceToControllerRating(addRatingRequest));
        com.fqts.rating.controller.model.RatingIdResponse  ratingIdResponse = ControllerToServiceRatingMapper.mapServiceToControllerRatingIDResponse(serviceResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingIdResponse);
    }
    @GetMapping("/getratingbyuserid/{userId}")
    public UserRatingResponse getRatingGivenByUserId(@PathVariable("userId") int userId){
        com.fqts.rating.service.model.UserRatingResponse serviceUserRatingResponse=ratingService.getRatingByUserId(userId);
        UserRatingResponse userRatingResponse = ControllerToServiceRatingMapper.mapServiceToControllerUserratingresponse(serviceUserRatingResponse);
        return userRatingResponse;
    }

    @GetMapping("/getcompanyrating/{companyId}")
    public CompanyRatingResponse getRatingforComapny(@PathVariable("companyId") int companyId){
        com.fqts.rating.service.model.CompanyRatingResponse serviceCompanyRatingResponse=ratingService.getRatingByCompanyId(companyId);
        CompanyRatingResponse companyRatingResponse = ControllerToServiceRatingMapper.mapServiceToControllerCompanyRatingResponse(serviceCompanyRatingResponse);
        return companyRatingResponse;
    }

    @GetMapping("/getcompanyaveragerating")
    public CompanyAverageRatingList getRatingsForAllCompanies() {
        List<com.fqts.rating.service.model.CompanyAverageRating> serviceCompanyRatingResponses = ratingService.getAllRatings();
        CompanyAverageRatingList companyAverageRatingList = new CompanyAverageRatingList();
        for (com.fqts.rating.service.model.CompanyAverageRating serviceCompanyRatingResponse : serviceCompanyRatingResponses) {
            CompanyAverageRating companyAverageRating = ControllerToServiceRatingMapper.mapServiceToControllerCompanyAverageRating(serviceCompanyRatingResponse);
            companyAverageRatingList.add(companyAverageRating);
        }
        return companyAverageRatingList;
    }



}
