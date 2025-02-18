package com.fqts.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String,Object>map = new HashMap<>();
        map.put("errorCode",CustomErrorCodes.USER_NOT_FOUND);
        map.put("error",ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String,Object>> handleDuplicateEmailException(DuplicateEmailException ex) {
        Map<String,Object>map = new HashMap<>();
        map.put("errorCode",CustomErrorCodes.DUPLICATE_EMAIL);
        map.put("error",ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(NotNullException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", CustomErrorCodes.NULL_VALUE);
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<Map<String,Object>> handleUnauthorizedAccess(UnauthorizedAccessException ex) {
        Map<String,Object>map = new HashMap<>();
        map.put("errorCode",CustomErrorCodes.UNAUTHORIZED_ACCESS);
        map.put("error",ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IdenticalPasswordException.class)
    public ResponseEntity<Map<String,Object>>  handleIdenticalPasswordException(IdenticalPasswordException ex) {
        Map<String,Object>map = new HashMap<>();
        map.put("errorCode",CustomErrorCodes.Identical_PASSWORD);
        map.put("error",ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>>  handleGenericException(Exception ex) {
        Map<String,Object>map = new HashMap<>();
        map.put("errorCode",CustomErrorCodes.UNKNOWN_ERROR);
        map.put("error",ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
