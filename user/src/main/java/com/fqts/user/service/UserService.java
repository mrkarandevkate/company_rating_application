package com.fqts.user.service;


import com.fqts.user.entity.Status;
import com.fqts.user.exception.UnauthorizedAccessException;
import com.fqts.user.exception.UserNotFoundException;
import com.fqts.user.repository.UserRepository;
import com.fqts.user.service.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fqts.user.mapper.ServiceToEntityUserMapper;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    public UserIdResponse adduser(CreateUserRequest createUserRequest) {
        try {
            com.fqts.user.entity.User savedEntity = userRepository.save(ServiceToEntityUserMapper.mapCreateUser(createUserRequest));
            return new UserIdResponse().userId(savedEntity.getUserID());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while adding the user", e);
        }
    }

    public UserIdResponse updateUser(UpdateUserRequest updateUserRequest) {
        try {
            com.fqts.user.entity.User savedEntity = userRepository.save(ServiceToEntityUserMapper.mapUpdateUser(updateUserRequest));
            return new UserIdResponse().userId(savedEntity.getUserID());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the user", e);
        }
    }


    public UserList getAllUserDetails() {
        try {
            List<com.fqts.user.entity.User> userListResult = userRepository.findAllUser();                    ;
            return ServiceToEntityUserMapper.mapEntityListToServiceUserList(userListResult);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the users", e);
        }
    }

    public User getUserDetailsByid(int userId) throws UnauthorizedAccessException {
        com.fqts.user.entity.User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        if (!user.getStatus().equals("ALLOWED")){
                throw new UnauthorizedAccessException("User with ID " + userId + " does not have access.");
        }
        return ServiceToEntityUserMapper.mapEntitytoServiceUser(user);
    }


    public void deletUserDetailsbyId(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with ID " + userId + " does not exist");
        }
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the user", e);
        }
    }

    public void updateUserPassword(UpdatePassword updatePassword) {
        com.fqts.user.entity.User user = userRepository.findByEmail(updatePassword.getEmail());
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + updatePassword.getEmail());
        }
        user.setPassword(updatePassword.getPassword());
        userRepository.save(user);
    }

    public boolean setStatus(int userId) {
        com.fqts.user.entity.User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        boolean statusChanged = false;
        if (user.getStatus().equals("NOTALLOWED")) {
            user.setStatus("ALLOWED");
            statusChanged = true;
        } else if (user.getStatus().equals("ALLOWED")) {
            user.setStatus("NOTALLOWED");
            statusChanged = false;
        }

        userRepository.save(user);
        return statusChanged;
    }

    public AdminCountResponse getAdminCount() {
        int admincount  = userRepository.countAllAdmin();
        AdminCountResponse adminCountResponse =ServiceToEntityUserMapper.MapAdminCount(admincount);
        return adminCountResponse;

    }

    public UserIdResponse addAdmin(CreateUserRequest createUserRequest) {
        try {
            com.fqts.user.entity.User savedEntity = userRepository.save(ServiceToEntityUserMapper.mapCreateAdmin(createUserRequest));
            return new UserIdResponse().userId(savedEntity.getUserID());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while adding the user", e);
        }
    }


    public LoginResponse loadUserByUserName(String email){
        com.fqts.user.entity.User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UnauthorizedAccessException("User's access has been revoked for this" + email);
        }
        else {
            return ServiceToEntityUserMapper.mapLoginReponse(user);
        }

    }
}
