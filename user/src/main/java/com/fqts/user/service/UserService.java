package com.fqts.user.service;

import com.fqts.user.service.model.*;

public interface UserService {

    UserIdResponse adduser(CreateUserRequest createUserRequest);

    UserIdResponse updateUser(UpdateUserRequest updateUserRequest);

    UserList getAllUserDetails();

    User getUserDetailsByid(int userId);

    void deleteUserDetailsById(int userId);

    void updateUserPassword(UpdatePassword updatePassword);

    boolean setStatus(int userId);

    AdminCountResponse getAdminCount();

    UserIdResponse addAdmin(CreateUserRequest createUserRequest);

    LoginResponse loadUserByUserName(String email);

    UserList getAllAdminDetails();
}
