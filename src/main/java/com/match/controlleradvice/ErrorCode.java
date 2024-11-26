package com.match.controlleradvice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("ERR-0000", HttpStatus.INTERNAL_SERVER_ERROR),
    MATCH_NOT_FOUND("ERR-0001", HttpStatus.BAD_REQUEST),
    MATCH_ODD_NOT_FOUND("ERR-0002", HttpStatus.BAD_REQUEST);

    private final String errorCode;
    private final HttpStatus httpStatus;
}
