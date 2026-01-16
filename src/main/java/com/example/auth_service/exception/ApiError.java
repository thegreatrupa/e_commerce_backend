package com.example.auth_service.exception;

import java.time.Instant;

public class ApiError {
    private final int status;
    private final String message;
    private final String errorCode;
    private final Instant timeStamp;

    public ApiError(int status, String message, String errorCode){
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.timeStamp = Instant.now();
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public String getErrorCode() { return errorCode; }
    public Instant getTimeStamp() { return timeStamp; }
}
