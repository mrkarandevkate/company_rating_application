/*
 * User Service
 * This is the API documentation for the User service from the Rating Service project, which is part of a microservice architecture for ratings.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.fqts.user.controller.model;
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
 * AdminCountResponse
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2025-02-05T14:36:03.766895818Z[GMT]")

public class AdminCountResponse {
  @SerializedName("adminCount")
  private Integer adminCount = null;

  public AdminCountResponse adminCount(Integer adminCount) {
    this.adminCount = adminCount;
    return this;
  }

   /**
   * Get adminCount
   * @return adminCount
  **/
  @Schema(description = "")
  public Integer getAdminCount() {
    return adminCount;
  }

  public void setAdminCount(Integer adminCount) {
    this.adminCount = adminCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdminCountResponse adminCountResponse = (AdminCountResponse) o;
    return Objects.equals(this.adminCount, adminCountResponse.adminCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adminCount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdminCountResponse {\n");
    
    sb.append("    adminCount: ").append(toIndentedString(adminCount)).append("\n");
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
