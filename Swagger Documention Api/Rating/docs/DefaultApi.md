# DefaultApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addratingPost**](DefaultApi.md#addratingPost) | **POST** /addrating | 
[**getcompanyaverageratingGet**](DefaultApi.md#getcompanyaverageratingGet) | **GET** /getcompanyaveragerating | 
[**getcompanyratingCompanyIdGet**](DefaultApi.md#getcompanyratingCompanyIdGet) | **GET** /getcompanyrating/{companyId} | 
[**getratingbyuseridUserIdGet**](DefaultApi.md#getratingbyuseridUserIdGet) | **GET** /getratingbyuserid/{userId} | 

<a name="addratingPost"></a>
# **addratingPost**
> RatingIdResponse addratingPost(body)



Adds a new rating to a company. This endpoint allows users to submit ratings along with optional comments for a specific company. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
AddRatingRequest body = new AddRatingRequest(); // AddRatingRequest | 
try {
    RatingIdResponse result = apiInstance.addratingPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#addratingPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**AddRatingRequest**](AddRatingRequest.md)|  | [optional]

### Return type

[**RatingIdResponse**](RatingIdResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getcompanyaverageratingGet"></a>
# **getcompanyaverageratingGet**
> CompanyAverageRatingList getcompanyaverageratingGet()



Fetches the average rating of a company based on customer feedback   or other relevant metrics.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    CompanyAverageRatingList result = apiInstance.getcompanyaverageratingGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getcompanyaverageratingGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CompanyAverageRatingList**](CompanyAverageRatingList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getcompanyratingCompanyIdGet"></a>
# **getcompanyratingCompanyIdGet**
> CompanyRatingResponse getcompanyratingCompanyIdGet(companyId)



Retrieves all ratings associated with a specific company. This endpoint requires the company ID as a path parameter. It returns the average rating of the company along with all ratings for the company, including comments, rating score, and associated user and company information. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Integer companyId = 56; // Integer | The unique identifier of the company.
try {
    CompanyRatingResponse result = apiInstance.getcompanyratingCompanyIdGet(companyId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getcompanyratingCompanyIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **companyId** | **Integer**| The unique identifier of the company. |

### Return type

[**CompanyRatingResponse**](CompanyRatingResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getratingbyuseridUserIdGet"></a>
# **getratingbyuseridUserIdGet**
> UserRatingResponse getratingbyuseridUserIdGet(userId)



Retrieves all ratings submitted by a specific user. This endpoint requires the user ID as a path parameter. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Integer userId = 56; // Integer | The unique identifier of the user.
try {
    UserRatingResponse result = apiInstance.getratingbyuseridUserIdGet(userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getratingbyuseridUserIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Integer**| The unique identifier of the user. |

### Return type

[**UserRatingResponse**](UserRatingResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

