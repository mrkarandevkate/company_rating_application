package com.fqts.rating.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalRatingExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", CustomErrorCodes.USER_NOT_FOUND);
        map.put("error", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCompanyNotFoundException(CompanyNotFoundException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", CustomErrorCodes.COMPANY_NOT_FOUND);
        map.put("error", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleRatingNotFoundException(RatingNotFoundException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", CustomErrorCodes.RATING_NOT_FOUND);
        map.put("error", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<Map<String, Object>> handleNotNullFieldException(NotNullException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", CustomErrorCodes.NULL_VALUE);
        map.put("error", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", CustomErrorCodes.UNKNOWN_ERROR);
        map.put("error", "An unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
