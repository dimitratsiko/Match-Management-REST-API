package com.match.controlleradvice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseCreator {

    public static ResponseEntity<?> getResponse(final HttpStatus status, final String clientError, final String clientDescription) {
        ErrorResponse errorResponse = new ErrorResponse(status, clientError, clientDescription);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    public static ResponseEntity<?> getResponse(final HttpStatus status, final String clientError, final String clientDescription, final String errorMessage) {
        ErrorResponse errorResponse = new ErrorResponse(status, clientError, clientDescription, errorMessage);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
