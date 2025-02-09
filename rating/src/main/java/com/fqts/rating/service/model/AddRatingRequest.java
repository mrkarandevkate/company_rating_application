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

package com.fqts.rating.service.model;

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
 * Schema representing the request payload for adding a new rating to a company. 
 */
@Schema(description = "Schema representing the request payload for adding a new rating to a company. ")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2025-02-01T09:34:38.541185546Z[GMT]")

public class AddRatingRequest {
  @SerializedName("userId")
  private Integer userId = null;

  @SerializedName("companyId")
  private Integer companyId = null;

  @SerializedName("rating")
  private Float rating = null;

  @SerializedName("comments")
  private String comments = null;

  public AddRatingRequest userId(Integer userId) {
    this.userId = userId;
    return this;
  }

   /**
   * The unique identifier of the user submitting the rating.
   * @return userId
  **/
  @Schema(description = "The unique identifier of the user submitting the rating.")
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public AddRatingRequest companyId(Integer companyId) {
    this.companyId = companyId;
    return this;
  }

   /**
   * The unique identifier of the company being rated.
   * @return companyId
  **/
  @Schema(description = "The unique identifier of the company being rated.")
  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }

  public AddRatingRequest rating(Float rating) {
    this.rating = rating;
    return this;
  }

   /**
   * The rating score given by the user, typically on a numerical scale (e.g., 1 to 5). 
   * @return rating
  **/
  @Schema(description = "The rating score given by the user, typically on a numerical scale (e.g., 1 to 5). ")
  public Float getRating() {
    return rating;
  }

  public void setRating(Float rating) {
    this.rating = rating;
  }

  public AddRatingRequest comments(String comments) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddRatingRequest addRatingRequest = (AddRatingRequest) o;
    return Objects.equals(this.userId, addRatingRequest.userId) &&
        Objects.equals(this.companyId, addRatingRequest.companyId) &&
        Objects.equals(this.rating, addRatingRequest.rating) &&
        Objects.equals(this.comments, addRatingRequest.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, companyId, rating, comments);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddRatingRequest {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    companyId: ").append(toIndentedString(companyId)).append("\n");
    sb.append("    rating: ").append(toIndentedString(rating)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
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
