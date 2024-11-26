package com.match.controlleradvice;

import com.match.common.exception.MatchNotFoundException;
import com.match.common.exception.MatchOddNotFoundException;
import com.match.controller.MatchController;
import com.match.controller.MatchOddController;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(assignableTypes = {MatchController.class, MatchOddController.class})
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {

    private static final ExceptionMapping GENERAL_ERROR = new ExceptionMapping(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus(),
            ErrorCode.INTERNAL_SERVER_ERROR.getErrorCode(),
            ErrorCode.INTERNAL_SERVER_ERROR.name());

    private final Map<Class<?>, ExceptionMapping> exceptionMappings = new HashMap<>();


    public ControllerErrorHandler() {
        addExceptionMapping(MatchNotFoundException.class, ErrorCode.MATCH_NOT_FOUND);
        addExceptionMapping(MatchOddNotFoundException.class, ErrorCode.MATCH_ODD_NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleThrowable(final Throwable ex, final HttpServletResponse response) {
        log.warn(ex.getMessage());
        ExceptionMapping mapping = exceptionMappings.getOrDefault(ex.getClass(), GENERAL_ERROR);
        return ErrorResponseCreator.getResponse(mapping.status, mapping.errorCode, mapping.errorDescription, ex.getMessage());
    }

    private void addExceptionMapping(
            final Class<?> theClass,
            final ErrorCode errorCode) {
        exceptionMappings.put(theClass, new ExceptionMapping(errorCode.getHttpStatus(), errorCode.getErrorCode(), errorCode.name()));
    }
}
