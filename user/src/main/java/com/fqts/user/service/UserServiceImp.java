package com.fqts.user.service;

import com.fqts.user.exception.*;
import com.fqts.user.mapper.ServiceToEntityUserMapper;
import com.fqts.user.repository.UserRepository;
import com.fqts.user.service.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService  {


    @Autowired
    private ServiceToEntityUserMapper serviceToEntityUserMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    public UserIdResponse adduser(CreateUserRequest createUserRequest) {
        if (createUserRequest.getName() == null || createUserRequest.getName().trim().isEmpty()) {
            throw new NotNullException("Name cannot be null or empty");
        }

        if (createUserRequest.getEmail() == null || createUserRequest.getEmail().trim().isEmpty()) {
            throw new NotNullException("Email cannot be null or empty");
        }

        if (createUserRequest.getPassword() == null || createUserRequest.getPassword().trim().isEmpty()) {
            throw new NotNullException("Password cannot be null or empty");
        }

        if (userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new DuplicateEmailException("Email already exists: " + createUserRequest.getEmail());
        }

        try {
            com.fqts.user.entity.User savedEntity = userRepository.save(serviceToEntityUserMapper.mapCreateUser(createUserRequest));
            return new UserIdResponse().userId(savedEntity.getUserID());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while adding the user", e);
        }
    }


public UserIdResponse updateUser(UpdateUserRequest updateUserRequest) {
    Optional<com.fqts.user.entity.User> existingUser = userRepository.findById(updateUserRequest.getUserId());
    if (updateUserRequest.getName() == null || updateUserRequest.getName().trim().isEmpty()) {
        throw new NotNullException("Name cannot be null or empty");
        }
    if (updateUserRequest.getEmail() == null || updateUserRequest.getEmail().trim().isEmpty()) {
        throw new NotNullException("Email cannot be null or empty");
    }

    if (!existingUser.isPresent()) {
        throw new RuntimeException("User with ID " + updateUserRequest.getUserId() + " does not exist.");
    }
    String existingPassword = existingUser.get().getPassword();
    com.fqts.user.entity.User userEntity = serviceToEntityUserMapper.mapUpdateUser(updateUserRequest, existingPassword);
    com.fqts.user.entity.User savedEntity = userRepository.save(userEntity);
    return new UserIdResponse().userId(savedEntity.getUserID());
}



    public UserList getAllUserDetails() {
        try {
            List<com.fqts.user.entity.User> userListResult = userRepository.findAllUser();
            return serviceToEntityUserMapper.mapEntityListToServiceUserList(userListResult);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the users", e);
        }
    }

    public User getUserDetailsByid(int userId) {
        com.fqts.user.entity.User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        if (!user.getStatus().equals("ALLOWED")) {
            throw new UnauthorizedAccessException("User with ID " + userId + " does not have access.");
        }

        return serviceToEntityUserMapper.mapEntitytoServiceUser(user);
    }


    public void deleteUserDetailsById(int userId) {
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
        if (updatePassword.getPassword() == null || updatePassword.getPassword().trim().isEmpty()) {
            throw new NotNullException("Password cannot be null or empty");
        }
        com.fqts.user.entity.User user = userRepository.findByEmail(updatePassword.getEmail());
        if(user.getPassword().equals(passwordEncoder.encode(updatePassword.getPassword()))){
            throw new IdenticalPasswordException("The new password cannot be the same as the old password.");
        }
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + updatePassword.getEmail());
        }
        user.setPassword(passwordEncoder.encode(updatePassword.getPassword()));
        userRepository.save(user);
    }

    public boolean setStatus(int userId) {
        com.fqts.user.entity.User user = userRepository.findById(userId)
                .orElseThrow(() ->new UserNotFoundException("User with ID " + userId + " not found"));
        boolean statusChanged = false;
        if (user.getStatus().equals("NOTALLOWED")) {
            user.setStatus("ALLOWED");
            user.setCreated_At(new Timestamp(System.currentTimeMillis()));
            statusChanged = true;
        } else if (user.getStatus().equals("ALLOWED")) {
            user.setStatus("NOTALLOWED");
            user.setCreated_At(new Timestamp(System.currentTimeMillis()));
            statusChanged = false;
        }
        userRepository.save(user);
        return statusChanged;
    }

    public AdminCountResponse getAdminCount() {
        int adminCount = userRepository.countAllAdmin();
        return serviceToEntityUserMapper.MapAdminCount(adminCount);
    }

    public UserIdResponse addAdmin(CreateUserRequest createUserRequest) {
        try {
            com.fqts.user.entity.User savedEntity = userRepository.save(serviceToEntityUserMapper.mapCreateAdmin(createUserRequest));
            return new UserIdResponse().userId(savedEntity.getUserID());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while adding the user", e);
        }
    }

    public LoginResponse loadUserByUserName(String email) {
        com.fqts.user.entity.User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UnauthorizedAccessException("User's access has been revoked for this " + email+"or user not found.");
        }
        return serviceToEntityUserMapper.mapLoginReponse(user);
    }

    @Override
    public UserList getAllAdminDetails() {
        try {
            List<com.fqts.user.entity.User> userListResult = userRepository.findAllAdmin();
            return serviceToEntityUserMapper.mapEntityListToServiceUserList(userListResult);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the users", e);
        }
    }
}
