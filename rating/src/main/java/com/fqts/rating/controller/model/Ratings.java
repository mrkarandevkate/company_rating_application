/*
 * Rating Service
 * This is the API documentation for the Rating Service, which is part of a microservices-based Rating Application.  This service allows users to add and retrieve ratings for companies, including user-specific and company-specific ratings. 
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.fqts.rating.controller.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * Schema representing an individual rating, including company details and additional metadata. 
 */
@Schema(description = "Schema representing an individual rating, including company details and additional metadata. ")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2025-02-01T09:34:38.541185546Z[GMT]")

public class Ratings {
  @SerializedName("ratingId")
  private Integer ratingId = null;

  @SerializedName("rating")
  private Float rating = null;

  @SerializedName("comments")
  private String comments = null;

  @SerializedName("created_at")
  private Object createdAt = null;

  @SerializedName("companydetails")
  private Company companydetails = null;

  public Ratings ratingId(Integer ratingId) {
    this.ratingId = ratingId;
    return this;
  }

   /**
   * The unique identifier of the rating.
   * @return ratingId
  **/
  @Schema(description = "The unique identifier of the rating.")
  public Integer getRatingId() {
    return ratingId;
  }

  public void setRatingId(Integer ratingId) {
    this.ratingId = ratingId;
  }

  public Ratings rating(Float rating) {
    this.rating = rating;
    return this;
  }

   /**
   * The rating score given by the user.
   * @return rating
  **/
  @Schema(description = "The rating score given by the user.")
  public Float getRating() {
    return rating;
  }

  public void setRating(Float rating) {
    this.rating = rating;
  }

  public Ratings comments(String comments) {
    this.comments = comments;
    return this;
  }

   /**
   * Optional comments provided by the user regarding the rating.
   * @return comments
  **/
  @Schema(description = "Optional comments provided by the user regarding the rating.")
  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Ratings createdAt(Object createdAt) {
    this.createdAt = createdAt;
    return this;
  }

   /**
   * The date when the rating was created.
   * @return createdAt
  **/
  @Schema(description = "The date when the rating was created.")
  public Object getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Object createdAt) {
    this.createdAt = createdAt;
  }

  public Ratings companydetails(Company companydetails) {
    this.companydetails = companydetails;
    return this;
  }

   /**
   * Get companydetails
   * @return companydetails
  **/
  @Schema(description = "")
  public Company getCompanydetails() {
    return companydetails;
  }

  public void setCompanydetails(Company companydetails) {
    this.companydetails = companydetails;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ratings ratings = (Ratings) o;
    return Objects.equals(this.ratingId, ratings.ratingId) &&
        Objects.equals(this.rating, ratings.rating) &&
        Objects.equals(this.comments, ratings.comments) &&
        Objects.equals(this.createdAt, ratings.createdAt) &&
        Objects.equals(this.companydetails, ratings.companydetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ratingId, rating, comments, createdAt, companydetails);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ratings {\n");
    
    sb.append("    ratingId: ").append(toIndentedString(ratingId)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    companydetails: ").append(toIndentedString(companydetails)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
