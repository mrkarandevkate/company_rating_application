package com.fqts.user.controller;

import com.fqts.user.controller.model.AdminCountResponse;
import com.fqts.user.controller.model.CreateUserRequest;
import com.fqts.user.controller.model.UserIdResponse;
import com.fqts.user.controller.model.UserList;
import com.fqts.user.mapper.ControllerToServiceUserMapper;
import com.fqts.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin API", description = "Operations for managing users and admins in the system.")
public class AdminController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Retrieve all users", description = "Fetches a list of all users registered in the system, including their details.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully")
    })
    @GetMapping("/getalluser")
    public ResponseEntity<UserList> getAllUser() {
        com.fqts.user.service.model.UserList serviceUserList = userService.getAllUserDetails();
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceUserMapper.mapServiceToControllerUserList(serviceUserList));
    }

    @Operation(summary = "Retrieve all admins", description = "Fetches a list of all admin users who have special privileges in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of admins retrieved successfully")
    })
    @GetMapping("/getalladmin")
    public ResponseEntity<UserList> getAllAdmin() {
        com.fqts.user.service.model.UserList serviceUserList = userService.getAllAdminDetails();
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceUserMapper.mapServiceToControllerUserList(serviceUserList));
    }

    @Operation(summary = "Add a new admin", description = "Registers a new admin user with required details and assigns admin privileges.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Admin created successfully")
    })
    @PostMapping("/addadmin")
    public ResponseEntity<UserIdResponse> addAdmin(@RequestBody CreateUserRequest createUserRequest) {
        com.fqts.user.service.model.UserIdResponse serviceUserIdResponse = userService.addAdmin(ControllerToServiceUserMapper.mapAdminUser(createUserRequest));
        UserIdResponse userIdResponse = ControllerToServiceUserMapper.mapServiceUsertIdToControlleruserId(serviceUserIdResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(userIdResponse);
    }

    @Operation(summary = "Change user access control", description = "Enables or disables access for a specific user based on their ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User access updated successfully")
    })
    @PutMapping("/access-control/{userId}")
    public ResponseEntity<Map<String, String>> setStatus(@Parameter(description = "User ID whose access control needs to be updated") @PathVariable("userId") int userId) {
        boolean statusChanged = userService.setStatus(userId);
        Map<String, String> response = new HashMap<>();

        if (statusChanged) {
            response.put("message", "User has been granted access");
        } else {
            response.put("message", "User's access has been revoked");
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get admin count", description = "Retrieves the total number of admin users currently registered in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin count retrieved successfully")
    })
    @GetMapping("/admincount")
    public ResponseEntity<AdminCountResponse> getAdminCount() {
        com.fqts.user.service.model.AdminCountResponse adminCountResponse = userService.getAdminCount();
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceUserMapper.mapServicetoControllerAdminCount(adminCountResponse));
    }
}
