package com.fqts.user.controller;

import com.fqts.user.controller.model.*;
import com.fqts.user.exception.UnauthorizedAccessException;
import com.fqts.user.mapper.ControllerToServiceUserMapper;
import com.fqts.user.service.UserService;
import com.fqts.user.service.UserServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    @PostMapping("/adduser")
    public ResponseEntity<UserIdResponse> addUser(@RequestBody CreateUserRequest createUserRequest){
        com.fqts.user.service.model.UserIdResponse serviceResponse = userService.adduser(ControllerToServiceUserMapper.mapCreateUser(createUserRequest));
        UserIdResponse userIdResponse = ControllerToServiceUserMapper.mapServiceUsertIdToControlleruserId(serviceResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(userIdResponse);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<UserIdResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest){
        com.fqts.user.service.model.UserIdResponse serviceResponse  = userService.updateUser(ControllerToServiceUserMapper.mapUpdateUser(updateUserRequest));
        UserIdResponse userIdResponse = ControllerToServiceUserMapper.mapServiceUsertIdToControlleruserId(serviceResponse);
        return ResponseEntity.status(HttpStatus.OK).body(userIdResponse);
    }



    @GetMapping("/getuser/{userId}")
    public ResponseEntity<User> getUserId(@PathVariable("userId") int userId) throws UnauthorizedAccessException {
        com.fqts.user.service.model.User serviceUser = userService.getUserDetailsByid(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceUserMapper.mapServiceToControllerUser(serviceUser));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId){
        userService.deleteUserDetailsById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User Deleted Successfully");
    }

    @PutMapping("/updatepassword")
    public ResponseEntity<String> UpadateUserPassword(@RequestBody UpdatePassword updatePassword){
        userService.updateUserPassword(ControllerToServiceUserMapper.mapUpdateUserPassword(updatePassword));
        return ResponseEntity.status(HttpStatus.OK).body("Password Updated Successfully");
    }

//    @PutMapping("/auth/access-control/{userId}")
//    public ResponseEntity<String> setStatus(@PathVariable("userId") int userId) {
//        boolean statusChanged = userService.setStatus(userId);
//        if (statusChanged) {
//            return ResponseEntity.ok("User has been granted access");
//        } else {
//            return ResponseEntity.ok("Student's access has been revoked");
//        }
//    }

    @GetMapping("/loaduser/{email}")
    public ResponseEntity<LoginResponse>loadByUser(@PathVariable("email") String email ){
        com.fqts.user.service.model.LoginResponse loginRespone = userService.loadUserByUserName(email);
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceUserMapper.mapLoginRespone(loginRespone));
    }
}
