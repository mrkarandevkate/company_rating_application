# DefaultApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addAdminPost**](DefaultApi.md#addAdminPost) | **POST** /addAdmin | 
[**adduserPost**](DefaultApi.md#adduserPost) | **POST** /adduser | 
[**admincountGet**](DefaultApi.md#admincountGet) | **GET** /admincount | 
[**authAccessControlUserIdPut**](DefaultApi.md#authAccessControlUserIdPut) | **PUT** /auth/access-control/{userId} | 
[**deleteuserUserIdDelete**](DefaultApi.md#deleteuserUserIdDelete) | **DELETE** /deleteuser/{userId} | 
[**getAlluserGet**](DefaultApi.md#getAlluserGet) | **GET** /getAlluser | 
[**loadByUsernameGet**](DefaultApi.md#loadByUsernameGet) | **GET** /loadByUsername | 
[**updatepasswordPut**](DefaultApi.md#updatepasswordPut) | **PUT** /updatepassword | 
[**updateuserPut**](DefaultApi.md#updateuserPut) | **PUT** /updateuser | 
[**userUserIdGet**](DefaultApi.md#userUserIdGet) | **GET** /user/{userId} | 

<a name="addAdminPost"></a>
# **addAdminPost**
> UserIdResponse addAdminPost(body)



Add Admin details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
CreateUserRequest body = new CreateUserRequest(); // CreateUserRequest | 
try {
    UserIdResponse result = apiInstance.addAdminPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#addAdminPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateUserRequest**](CreateUserRequest.md)|  |

### Return type

[**UserIdResponse**](UserIdResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="adduserPost"></a>
# **adduserPost**
> UserIdResponse adduserPost(body)



Add User details

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
CreateUserRequest body = new CreateUserRequest(); // CreateUserRequest | 
try {
    UserIdResponse result = apiInstance.adduserPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#adduserPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateUserRequest**](CreateUserRequest.md)|  |

### Return type

[**UserIdResponse**](UserIdResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="admincountGet"></a>
# **admincountGet**
> AdminCountResponse admincountGet()



Retrieve the current count of admins.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    AdminCountResponse result = apiInstance.admincountGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#admincountGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**AdminCountResponse**](AdminCountResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="authAccessControlUserIdPut"></a>
# **authAccessControlUserIdPut**
> authAccessControlUserIdPut(userId)



Toggle the &#x27;is_active&#x27; status of a user. If the current status is 1 (active), set it to 0 (inactive). If the current status is 0 (inactive), set it to 1 (active).

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Integer userId = 56; // Integer | The ID of the user whose status needs to be updated.
try {
    apiInstance.authAccessControlUserIdPut(userId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#authAccessControlUserIdPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Integer**| The ID of the user whose status needs to be updated. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="deleteuserUserIdDelete"></a>
# **deleteuserUserIdDelete**
> deleteuserUserIdDelete(userId)



Delete a user from the system by userId

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Integer userId = 56; // Integer | The ID of the user to delete
try {
    apiInstance.deleteuserUserIdDelete(userId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#deleteuserUserIdDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Integer**| The ID of the user to delete |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getAlluserGet"></a>
# **getAlluserGet**
> UserList getAlluserGet()



Retrieve all users who are active (is_active &#x3D; 1).

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    UserList result = apiInstance.getAlluserGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#getAlluserGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**UserList**](UserList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="loadByUsernameGet"></a>
# **loadByUsernameGet**
> LoginResponse loadByUsernameGet(email)



Retrieve User details by email address.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String email = "email_example"; // String | The email address of the user to retrieve
try {
    LoginResponse result = apiInstance.loadByUsernameGet(email);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#loadByUsernameGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **email** | **String**| The email address of the user to retrieve |

### Return type

[**LoginResponse**](LoginResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updatepasswordPut"></a>
# **updatepasswordPut**
> updatepasswordPut(body)



Update User Password. Checks if the user exists by email before updating the password.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
UpdatePassword body = new UpdatePassword(); // UpdatePassword | 
try {
    apiInstance.updatepasswordPut(body);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#updatepasswordPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UpdatePassword**](UpdatePassword.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="updateuserPut"></a>
# **updateuserPut**
> UserIdResponse updateuserPut(body)



Update User details 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
UpdateUserRequest body = new UpdateUserRequest(); // UpdateUserRequest | 
try {
    UserIdResponse result = apiInstance.updateuserPut(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#updateuserPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UpdateUserRequest**](UpdateUserRequest.md)|  |

### Return type

[**UserIdResponse**](UserIdResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="userUserIdGet"></a>
# **userUserIdGet**
> User userUserIdGet(userId)



Get  User detail by user id who is active (is_active &#x3D; 1)

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
Integer userId = 56; // Integer | The ID of the user to retrieve
try {
    User result = apiInstance.userUserIdGet(userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#userUserIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Integer**| The ID of the user to retrieve |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

