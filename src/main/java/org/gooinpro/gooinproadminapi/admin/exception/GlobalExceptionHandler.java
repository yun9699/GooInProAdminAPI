package org.gooinpro.gooinproadminapi.admin.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(AdminTaskException.class)
    public ResponseEntity<Map<String, Object>> handleAdminException(AdminTaskException e) {
        log.error("AdminException: {}", e.getMessage());

        return ResponseEntity
                .status(e.getStatus())
                .body(Map.of("msg", e.getMsg(), "status", e.getStatus()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, Object>> handleBindException(BindException e) {
        log.error("BindException: {}", e.getMessage());

        return ResponseEntity
                .badRequest()
                .body(Map.of("msg", e.getMessage(), "status", 400));
    }
}
