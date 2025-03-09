package com.fqts.user.controller;

import com.fqts.user.controller.model.*;
import com.fqts.user.exception.UnauthorizedAccessException;
import com.fqts.user.mapper.ControllerToServiceUserMapper;
import com.fqts.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User API", description = "APIs for managing users, including creation, retrieval, updating, and deletion.")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Operation(summary = "Add a new user", description = "Registers a new user in the system with provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully")
    })
    @PostMapping("/adduser")
    public ResponseEntity<UserIdResponse> addUser(@RequestBody CreateUserRequest createUserRequest) {
        com.fqts.user.service.model.UserIdResponse serviceResponse = userService.adduser(ControllerToServiceUserMapper.mapCreateUser(createUserRequest));
        UserIdResponse userIdResponse = ControllerToServiceUserMapper.mapServiceUsertIdToControlleruserId(serviceResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(userIdResponse);
    }

    @Operation(summary = "Update an existing user", description = "Modifies details of an existing user based on input data.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully")
    })
    @PutMapping("/updateuser")
    public ResponseEntity<UserIdResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        com.fqts.user.service.model.UserIdResponse serviceResponse = userService.updateUser(ControllerToServiceUserMapper.mapUpdateUser(updateUserRequest));
        UserIdResponse userIdResponse = ControllerToServiceUserMapper.mapServiceUsertIdToControlleruserId(serviceResponse);
        return ResponseEntity.status(HttpStatus.OK).body(userIdResponse);
    }

    @Operation(summary = "Get user details by ID", description = "Retrieves user details using the provided user ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User details retrieved successfully")
    })
    @GetMapping("/getuser/{userId}")
    public ResponseEntity<User> getUserId(@Parameter(description = "User ID to retrieve details") @PathVariable("userId") int userId) throws UnauthorizedAccessException {
        com.fqts.user.service.model.User serviceUser = userService.getUserDetailsByid(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceUserMapper.mapServiceToControllerUser(serviceUser));
    }

    @Operation(summary = "Delete a user", description = "Removes a user from the system based on their ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully")
    })
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@Parameter(description = "User ID to delete") @PathVariable("userId") int userId) {
        userService.deleteUserDetailsById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User Deleted Successfully");
    }

    @Operation(summary = "Update user password", description = "Changes the password of an existing user securely.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password updated successfully")
    })
    @PutMapping("/updatepassword")
    public ResponseEntity<String> updateUserPassword(@RequestBody UpdatePassword updatePassword) {
        userService.updateUserPassword(ControllerToServiceUserMapper.mapUpdateUserPassword(updatePassword));
        return ResponseEntity.status(HttpStatus.OK).body("Password Updated Successfully");
    }

    @Operation(summary = "Load user by email", description = "Fetches user details using their registered email address.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User details retrieved successfully")
    })

    @GetMapping("/loaduser/{email}")
    public ResponseEntity<LoginResponse> loadByUser(@Parameter(description = "User email address") @PathVariable("email") String email) {
        com.fqts.user.service.model.LoginResponse loginResponse = userService.loadUserByUserName(email);
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceUserMapper.mapLoginRespone(loginResponse));
    }
}
