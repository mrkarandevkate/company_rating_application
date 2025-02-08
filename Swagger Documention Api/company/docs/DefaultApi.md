# DefaultApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addcompanyPost**](DefaultApi.md#addcompanyPost) | **POST** /addcompany | 
[**companyCompanyIdGet**](DefaultApi.md#companyCompanyIdGet) | **GET** /company/{companyId} | 
[**deletecompanyCompanyIdDelete**](DefaultApi.md#deletecompanyCompanyIdDelete) | **DELETE** /deletecompany/{companyId} | 
[**getallcompanyGet**](DefaultApi.md#getallcompanyGet) | **GET** /getallcompany | 
[**updatecompanyPut**](DefaultApi.md#updatecompanyPut) | **PUT** /updatecompany | 

<a name="addcompanyPost"></a>
# **addcompanyPost**
> CompanyIdResponse addcompanyPost(body)



Add Company details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
CreateCompanyRequest body = new CreateCompanyRequest(); // CreateCompanyRequest | 
try {
    CompanyIdResponse result = apiInstance.addcompanyPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#addcompanyPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateCompanyRequest**](CreateCompanyRequest.md)|  |

### Return type

[**CompanyIdResponse**](CompanyIdResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="companyCompanyIdGet"></a>
# **companyCompanyIdGet**
> Company companyCompanyIdGet(companyId)



Get Company detail by Company id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Integer companyId = 56; // Integer | The ID of the Company to retrieve
try {
    Company result = apiInstance.companyCompanyIdGet(companyId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#companyCompanyIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **companyId** | **Integer**| The ID of the Company to retrieve |

### Return type

[**Company**](Company.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deletecompanyCompanyIdDelete"></a>
# **deletecompanyCompanyIdDelete**
> deletecompanyCompanyIdDelete(companyId)



Delete a Company from the system by companyId

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Integer companyId = 56; // Integer | The ID of the Company to delete
try {
    apiInstance.deletecompanyCompanyIdDelete(companyId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deletecompanyCompanyIdDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **companyId** | **Integer**| The ID of the Company to delete |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getallcompanyGet"></a>
# **getallcompanyGet**
> CompanyList getallcompanyGet()



Get all company details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    CompanyList result = apiInstance.getallcompanyGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getallcompanyGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CompanyList**](CompanyList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updatecompanyPut"></a>
# **updatecompanyPut**
> CompanyIdResponse updatecompanyPut(body)



Update Company details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
CompanyUpdateRequest body = new CompanyUpdateRequest(); // CompanyUpdateRequest | 
try {
    CompanyIdResponse result = apiInstance.updatecompanyPut(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#updatecompanyPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CompanyUpdateRequest**](CompanyUpdateRequest.md)|  |

### Return type

[**CompanyIdResponse**](CompanyIdResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

