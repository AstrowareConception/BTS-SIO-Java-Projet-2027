package com.astroware.bts.media.controller;

import com.astroware.bts.media.service.AssetNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(AssetNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFound(
        AssetNotFoundException ex,
        HttpServletRequest request
    ) {
        return error(HttpStatus.NOT_FOUND, "ASSET_NOT_FOUND", ex.getMessage(), request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> badRequest(
        IllegalArgumentException ex,
        HttpServletRequest request
    ) {
        return error(HttpStatus.UNPROCESSABLE_ENTITY, "VALIDATION_ERROR", ex.getMessage(), request);
    }

    private ResponseEntity<Map<String, Object>> error(
        HttpStatus status,
        String code,
        String message,
        HttpServletRequest request
    ) {
        return ResponseEntity.status(status).body(Map.of(
            "timestamp", Instant.now().toString(),
            "status", status.value(),
            "code", code,
            "message", message,
            "path", request.getRequestURI()
        ));
    }
}
