package com.example.auth_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiError> handleInvalidCredential(InvalidCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiError(401, ex.getMessage(), "AUTH_INVALID_CREDENTIALS"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(404, ex.getMessage(), "RESOURSE_NOT_FOUND"));
    }

    @ExceptionHandler(java.lang.Exception.class)
    public ResponseEntity<ApiError> handleGeneric(java.lang.Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(500, "Something Went Wrong", "INTERNAL_ERROR"));
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleConflict(org.springframework.dao.DataIntegrityViolationException ex) {
        String message = "This username or email is already taken. Please try a different one.";
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(409, message, "USER_ALREADY_EXISTS"));
    }
}
