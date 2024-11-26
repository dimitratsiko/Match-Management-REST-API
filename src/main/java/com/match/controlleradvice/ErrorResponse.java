package com.match.controlleradvice;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
public class ErrorResponse implements Serializable {

    private HttpStatus status;
    private String errorCode;
    private String errorDescription;
    private String errorMessage;

    public ErrorResponse(final HttpStatus status, final String errorCode, final String errorDescription, final String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.errorMessage = errorMessage;
    }

    public ErrorResponse(final HttpStatus status, final String errorCode, final String errorDescription) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
