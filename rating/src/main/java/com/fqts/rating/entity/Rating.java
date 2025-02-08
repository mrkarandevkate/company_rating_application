package com.fqts.rating.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int RatingId;
    private int userId;
    private int companyId;
    private float rating;
    private String comment;

    public Rating() {
    }

    public Rating(int ratingId, int userId, int companyId, float rating, String comment, Date created_At) {
        RatingId = ratingId;
        this.userId = userId;
        this.companyId = companyId;
        this.rating = rating;
        this.comment = comment;
        this.created_At = created_At;
    }

    public int getRatingId() {
        return RatingId;
    }

    public void setRatingId(int ratingId) {
        RatingId = ratingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    @Temporal(TemporalType.DATE)
    private Date created_At= new Date();


}
