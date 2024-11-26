package com.match.common.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class LogUtils {
    public static void logExecution(String className, String methodName, Object param, Long executionTime) {
        if (log.isInfoEnabled()) {
            log.info("Execution - {}.{} | Duration: {}ms | Params: {}", className, methodName, executionTime, maskSensitiveParams(param));
        }
    }
    public static void logError(String className, String methodName, Exception exception) {
        if (log.isErrorEnabled()) {
            log.error("Error in {}.{}: {}", className, methodName, exception.getMessage(), exception);
        }
    }
    private static Object maskSensitiveParams(Object param) {
        if (Objects.isNull(param)) return null;
        String paramString = param.toString();
        if (paramString.contains("password") || paramString.contains("apiKey")) {
            return "[REDACTED]";
        }
        return param;
    }
}
