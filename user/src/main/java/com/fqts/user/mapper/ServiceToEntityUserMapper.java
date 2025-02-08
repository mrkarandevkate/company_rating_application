package com.fqts.user.mapper;

import com.fqts.user.entity.User;
import com.fqts.user.service.UserService;
import com.fqts.user.service.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class ServiceToEntityUserMapper {

    public static com.fqts.user.entity.User mapCreateUser(CreateUserRequest createUserRequest)
    {
        com.fqts.user.entity.User userEntity = new com.fqts.user.entity.User();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPassword(createUserRequest.getPassword());
        userEntity.setStatus("NOTALLOWED");
        userEntity.setRole("USER");
        return userEntity;
    }


    public static com.fqts.user.entity.User mapUpdateUser(UpdateUserRequest updateUserRequest){
        com.fqts.user.entity.User userUpdateEntity = new com.fqts.user.entity.User();
        userUpdateEntity.setUserID(updateUserRequest.getUserId());
        userUpdateEntity.setName(updateUserRequest.getName());
        userUpdateEntity.setEmail(updateUserRequest.getEmail());
        userUpdateEntity.setRole("USER");
        if(userUpdateEntity.getStatus()=="ALLOWED") {
            userUpdateEntity.setStatus("ALLOWED");
        }
        else{
            userUpdateEntity.setStatus("NOTALLOWED");
        }
        return userUpdateEntity;
    }

    public static UserList mapEntityListToServiceUserList(List<User> userListResult) {
        UserList userList = new UserList();
        for(com.fqts.user.entity.User entityUser:userListResult){
            com.fqts.user.service.model.User user = new com.fqts.user.service.model.User();
            user.setUserId(entityUser.getUserID());
            user.setName(entityUser.getName());
            user.setEmail(entityUser.getEmail());
            user.setCreatedAt(entityUser.getCreated_At());
            user.setStatus(entityUser.getStatus());
            userList.add(user);
        }
        return userList;
    }

    public static com.fqts.user.service.model.User mapEntitytoServiceUser(com.fqts.user.entity.User user) {
        com.fqts.user.service.model.User serviceUser = new com.fqts.user.service.model.User();
        serviceUser.setUserId(user.getUserID());
        serviceUser.setName(user.getName());
        serviceUser.setEmail(user.getEmail());
        serviceUser.setCreatedAt(user.getCreated_At());
        serviceUser.setStatus(user.getStatus());

        return serviceUser;
    }
    public static AdminCountResponse MapAdminCount(int admincount) {
        AdminCountResponse adminCountResponse = new AdminCountResponse();
        adminCountResponse.setAdminCount(admincount);
        return  adminCountResponse;
    }

    public static User mapCreateAdmin(CreateUserRequest createUserRequest) {
        com.fqts.user.entity.User userEntity = new com.fqts.user.entity.User();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPassword(createUserRequest.getPassword());
        userEntity.setStatus("ALLOWED");
        userEntity.setRole("ADMIN");
        return userEntity;
    }

    public static LoginResponse mapLoginReponse(User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(user.getUserID());
        loginResponse.setName(user.getName());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setPassword(user.getPassword());
        return  loginResponse;
    }
}
