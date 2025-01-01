package com.example.yhproject.creditserver.config;

import com.example.yhproject.creditserver.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(NoSuchElementException e) {
        log.warn("[BAD REQUEST] {}", e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        log.error("[INTERNAL SERVER ERROR]", e);
        return ResponseEntity.internalServerError().body(new ErrorResponse(e.getMessage()));
    }

}
