package com.fqts.user.controller;

import com.fqts.user.controller.model.AdminCountResponse;
import com.fqts.user.controller.model.CreateUserRequest;
import com.fqts.user.controller.model.UserIdResponse;
import com.fqts.user.controller.model.UserList;
import com.fqts.user.mapper.ControllerToServiceUserMapper;
import com.fqts.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/getalluser")
    public ResponseEntity<UserList> getAllUser(){
        com.fqts.user.service.model.UserList serviceUserList = userService.getAllUserDetails();
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceUserMapper.mapServiceToControllerUserList(serviceUserList));
    }

    @PostMapping("/addadmin")
    public ResponseEntity<UserIdResponse> addAdmin(CreateUserRequest createUserRequest){
        com.fqts.user.service.model.UserIdResponse serviceUserIdResponse = userService.addAdmin(ControllerToServiceUserMapper.mapAdminUser(createUserRequest));
        UserIdResponse userIdResponse =ControllerToServiceUserMapper.mapServiceUsertIdToControlleruserId(serviceUserIdResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(userIdResponse);
    }

    @PutMapping("/auth/access-control/{userId}")
    public ResponseEntity<Map<String, String>> setStatus(@PathVariable("userId") int userId) {
        boolean statusChanged = userService.setStatus(userId);
        Map<String, String> response = new HashMap<>(   );

        if (statusChanged) {
            response.put("message", "User has been granted access");
        } else {
            response.put("message", "User's access has been revoked");
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/admincount")
    public ResponseEntity<AdminCountResponse>getAdminCount(){
        com.fqts.user.service.model.AdminCountResponse adminCountResponse= userService.getAdminCount();
        return ResponseEntity.status(HttpStatus.OK).body(ControllerToServiceUserMapper.mapServicetoControllerAdminCount(adminCountResponse));
    }
}
