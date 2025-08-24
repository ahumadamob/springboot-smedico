package com.imb2025.smedico.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.imb2025.smedico.dto.ApiResponseErrorDto;
import com.imb2025.smedico.dto.FieldErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        List<FieldErrorDto> errors = new ArrayList<>();
        errors.add(new FieldErrorDto("error", ex.getMessage()));
        ApiResponseErrorDto response = new ApiResponseErrorDto(false, errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseErrorDto> handleGenericException(Exception ex) {
        List<FieldErrorDto> errors = new ArrayList<>();
        errors.add(new FieldErrorDto("error", ex.getMessage()));
        ApiResponseErrorDto response = new ApiResponseErrorDto(false, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

