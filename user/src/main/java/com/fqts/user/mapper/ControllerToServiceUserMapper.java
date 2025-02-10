package com.fqts.user.mapper;

import com.fqts.user.controller.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerToServiceUserMapper {
    private static final Logger log = LoggerFactory.getLogger(ControllerToServiceUserMapper.class);

    public static com.fqts.user.service.model.CreateUserRequest mapCreateUser(CreateUserRequest createUserRequest){
        com.fqts.user.service.model.CreateUserRequest userCreateEntityService = new com.fqts.user.service.model.CreateUserRequest();
        userCreateEntityService.setName(createUserRequest.getName());
        userCreateEntityService.setEmail(createUserRequest.getEmail());
        userCreateEntityService.setPassword(createUserRequest.getPassword());
        return userCreateEntityService;
    }

    public static com.fqts.user.service.model.UpdateUserRequest mapUpdateUser(UpdateUserRequest updateUserRequest){
        com.fqts.user.service.model.UpdateUserRequest userUpdateEntityService = new com.fqts.user.service.model.UpdateUserRequest();
        userUpdateEntityService.setUserId(updateUserRequest.getUserId());
        userUpdateEntityService.setName(updateUserRequest.getName());
        userUpdateEntityService.setEmail(updateUserRequest.getEmail());
        return userUpdateEntityService;
    }

    public static UserIdResponse mapServiceUsertIdToControlleruserId(com.fqts.user.service.model.UserIdResponse serviceResponse) {
        return new UserIdResponse().userId(serviceResponse.getUserId());
    }

    public static UserList mapServiceToControllerUserList(com.fqts.user.service.model.UserList serviceUserList) {
        com.fqts.user.controller.model.UserList controllerUserList = new com.fqts.user.controller.model.UserList();

        for (com.fqts.user.service.model.User serviceUser : serviceUserList) {
            com.fqts.user.controller.model.User controllerUser = new com.fqts.user.controller.model.User();
            controllerUser.setUserId(serviceUser.getUserId());
            controllerUser.setName(serviceUser.getName());
            controllerUser.setEmail(serviceUser.getEmail());
            controllerUser.setStatus(serviceUser.getStatus());
            controllerUser.setCreatedAt(serviceUser.getCreatedAt()); // Adjust field names if necessary// Add the mapped user to the controller's UserList

            controllerUserList.add(controllerUser);
        }
        return controllerUserList;
    }

    public static User mapServiceToControllerUser(com.fqts.user.service.model.User serviceUser) {
        User controllerUser= new User();
        controllerUser.setUserId(serviceUser.getUserId());
        controllerUser.setName(serviceUser.getName());
        controllerUser.setEmail(serviceUser.getEmail());
        controllerUser.setStatus(serviceUser.getStatus());
        controllerUser.setCreatedAt(serviceUser.getCreatedAt());
        return controllerUser;
    }

    public static com.fqts.user.service.model.UpdatePassword mapUpdateUserPassword(UpdatePassword controllerUpdatePassword) {
        com.fqts.user.service.model.UpdatePassword serviceUpdatePassword = new  com.fqts.user.service.model.UpdatePassword();
        serviceUpdatePassword.setEmail(controllerUpdatePassword.getEmail());
        serviceUpdatePassword.setPassword(controllerUpdatePassword.getPassword());
        return serviceUpdatePassword;
    }

    public static AdminCountResponse mapServicetoControllerAdminCount(com.fqts.user.service.model.AdminCountResponse adminCountResponse) {
            AdminCountResponse adminCount = new AdminCountResponse();
            adminCount.setAdminCount(adminCountResponse.getAdminCount());
            return  adminCount;
    }

    public static com.fqts.user.service.model.CreateUserRequest mapAdminUser(CreateUserRequest createUserRequest) {
        com.fqts.user.service.model.CreateUserRequest userCreateEntityService = new com.fqts.user.service.model.CreateUserRequest();
        userCreateEntityService.setName(createUserRequest.getName());
        userCreateEntityService.setEmail(createUserRequest.getEmail());
        userCreateEntityService.setPassword(createUserRequest.getPassword());
        return userCreateEntityService;
    }

    public static LoginResponse mapLoginRespone(com.fqts.user.service.model.LoginResponse serviceloginRespone) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(serviceloginRespone.getUserId());
        loginResponse.setEmail(serviceloginRespone.getEmail());
        loginResponse.setName(serviceloginRespone.getName());
        loginResponse.setPassword(serviceloginRespone.getPassword());
        loginResponse.setRole(serviceloginRespone.getRole());
        return loginResponse;
    }
}
