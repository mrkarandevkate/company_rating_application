# swagger-java-client

User Service
- API version: 1.0.0
  - Build date: 2025-02-10T05:19:44.197839517Z[GMT]

This is the API documentation for the User service from the Rating Service project, which is part of a microservice architecture for ratings.


*Automatically generated by the [Swagger Codegen](https://github.com/swagger-api/swagger-codegen)*


## Requirements

Building the API client library requires:
1. Java 1.7+
2. Maven/Gradle

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>io.swagger</groupId>
  <artifactId>swagger-java-client</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/swagger-java-client-1.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        CreateUserRequest body = new CreateUserRequest(); // CreateUserRequest | 
        try {
            UserIdResponse result = apiInstance.addAdminPost(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#addAdminPost");
            e.printStackTrace();
        }
    }
}
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        CreateUserRequest body = new CreateUserRequest(); // CreateUserRequest | 
        try {
            UserIdResponse result = apiInstance.adduserPost(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#adduserPost");
            e.printStackTrace();
        }
    }
}
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        try {
            AdminCountResponse result = apiInstance.admincountGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#admincountGet");
            e.printStackTrace();
        }
    }
}
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        Integer userId = 56; // Integer | The ID of the user whose status needs to be updated.
        try {
            apiInstance.authAccessControlUserIdPut(userId);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#authAccessControlUserIdPut");
            e.printStackTrace();
        }
    }
}
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        Integer userId = 56; // Integer | The ID of the user to delete
        try {
            apiInstance.deleteuserUserIdDelete(userId);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#deleteuserUserIdDelete");
            e.printStackTrace();
        }
    }
}
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        try {
            UserList result = apiInstance.getAlluserGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#getAlluserGet");
            e.printStackTrace();
        }
    }
}
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        String email = "email_example"; // String | The email address of the user to retrieve
        try {
            LoginResponse result = apiInstance.loadByUsernameGet(email);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#loadByUsernameGet");
            e.printStackTrace();
        }
    }
}
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        UpdatePassword body = new UpdatePassword(); // UpdatePassword | 
        try {
            apiInstance.updatepasswordPut(body);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#updatepasswordPut");
            e.printStackTrace();
        }
    }
}
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        UpdateUserRequest body = new UpdateUserRequest(); // UpdateUserRequest | 
        try {
            UserIdResponse result = apiInstance.updateuserPut(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#updateuserPut");
            e.printStackTrace();
        }
    }
}
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        Integer userId = 56; // Integer | The ID of the user to retrieve
        try {
            User result = apiInstance.userUserIdGet(userId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#userUserIdGet");
            e.printStackTrace();
        }
    }
}
```

## Documentation for API Endpoints

All URIs are relative to */*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**addAdminPost**](docs/DefaultApi.md#addAdminPost) | **POST** /addAdmin | 
*DefaultApi* | [**adduserPost**](docs/DefaultApi.md#adduserPost) | **POST** /adduser | 
*DefaultApi* | [**admincountGet**](docs/DefaultApi.md#admincountGet) | **GET** /admincount | 
*DefaultApi* | [**authAccessControlUserIdPut**](docs/DefaultApi.md#authAccessControlUserIdPut) | **PUT** /auth/access-control/{userId} | 
*DefaultApi* | [**deleteuserUserIdDelete**](docs/DefaultApi.md#deleteuserUserIdDelete) | **DELETE** /deleteuser/{userId} | 
*DefaultApi* | [**getAlluserGet**](docs/DefaultApi.md#getAlluserGet) | **GET** /getAlluser | 
*DefaultApi* | [**loadByUsernameGet**](docs/DefaultApi.md#loadByUsernameGet) | **GET** /loadByUsername | 
*DefaultApi* | [**updatepasswordPut**](docs/DefaultApi.md#updatepasswordPut) | **PUT** /updatepassword | 
*DefaultApi* | [**updateuserPut**](docs/DefaultApi.md#updateuserPut) | **PUT** /updateuser | 
*DefaultApi* | [**userUserIdGet**](docs/DefaultApi.md#userUserIdGet) | **GET** /user/{userId} | 

## Documentation for Models

 - [AdminCountResponse](docs/AdminCountResponse.md)
 - [CreateUserRequest](docs/CreateUserRequest.md)
 - [LoginResponse](docs/LoginResponse.md)
 - [UpdatePassword](docs/UpdatePassword.md)
 - [UpdateUserRequest](docs/UpdateUserRequest.md)
 - [User](docs/User.md)
 - [UserIdResponse](docs/UserIdResponse.md)
 - [UserList](docs/UserList.md)

## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author


