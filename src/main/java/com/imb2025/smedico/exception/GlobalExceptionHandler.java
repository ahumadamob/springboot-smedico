package com.imb2025.smedico.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.imb2025.smedico.dto.ApiResponseErrorDto;
import com.imb2025.smedico.dto.FieldErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 de dominio
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseErrorDto> handleResourceNotFound(ResourceNotFoundException ex) {
        List<FieldErrorDto> errors = List.of(new FieldErrorDto("error", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseErrorDto(false, errors));
    }

    // DTOs validados con @Valid en @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseErrorDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldErrorDto> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new FieldErrorDto(fe.getField(), messageOrCode(fe)))
                .collect(Collectors.toList());

        // También agregamos errores globales (sin campo específico) si los hubiera
        ex.getBindingResult().getGlobalErrors().forEach(ge ->
                errors.add(new FieldErrorDto(ge.getObjectName(), ge.getDefaultMessage()))
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseErrorDto(false, errors));
    }

    // Validación en parámetros (query/path) con @Validated + @NotBlank, @Min, etc.
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponseErrorDto> handleConstraintViolation(ConstraintViolationException ex) {
        List<FieldErrorDto> errors = ex.getConstraintViolations()
                .stream()
                .map(this::toFieldError)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseErrorDto(false, errors));
    }

    // Binding/validación en @ModelAttribute o cuando no es RequestBody
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponseErrorDto> handleBindException(BindException ex) {
        List<FieldErrorDto> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new FieldErrorDto(fe.getField(), messageOrCode(fe)))
                .collect(Collectors.toList());

        ex.getBindingResult().getGlobalErrors().forEach(ge ->
                errors.add(new FieldErrorDto(ge.getObjectName(), ge.getDefaultMessage()))
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseErrorDto(false, errors));
    }

    // Falta un parámetro requerido en query
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponseErrorDto> handleMissingParam(MissingServletRequestParameterException ex) {
        List<FieldErrorDto> errors = List.of(
                new FieldErrorDto(ex.getParameterName(),
                        "Parámetro requerido ausente: " + ex.getParameterName())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseErrorDto(false, errors));
    }

    // JSON malformado o tipo inválido en el body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseErrorDto> handleNotReadable(HttpMessageNotReadableException ex) {
        List<FieldErrorDto> errors = List.of(new FieldErrorDto("body", "Cuerpo de la solicitud inválido o mal formado"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseErrorDto(false, errors));
    }

    // Tipo inválido en path variable / query param (e.g., esperaba Long y llegó "abc")
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponseErrorDto> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String name = ex.getName();
        String expected = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "tipo esperado";
        String msg = "Valor inválido para '" + name + "'. Se esperaba " + expected + ".";
        List<FieldErrorDto> errors = List.of(new FieldErrorDto(name, msg));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseErrorDto(false, errors));
    }

    // (Opcional) Método HTTP no soportado -> no es de validación pero útil
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponseErrorDto> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        List<FieldErrorDto> errors = List.of(new FieldErrorDto("method", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ApiResponseErrorDto(false, errors));
    }

    // Fallback genérico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseErrorDto> handleGeneric(Exception ex) {
        List<FieldErrorDto> errors = List.of(new FieldErrorDto("error", ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseErrorDto(false, errors));
    }

    // ---- Helpers ----

    private FieldErrorDto toFieldError(ConstraintViolation<?> cv) {
        // Extrae el "leaf" del path como nombre de campo
        String field = StreamSupport.stream(cv.getPropertyPath().spliterator(), false)
                .reduce((first, second) -> second) // último nodo
                .map(node -> node.getName() != null ? node.getName() : "parameter")
                .orElse("parameter");
        String message = cv.getMessage();
        return new FieldErrorDto(field, message);
    }

    private String messageOrCode(FieldError fe) {
        // Usa el defaultMessage si está, si no cae al código/rejectValue
        if (fe.getDefaultMessage() != null && !fe.getDefaultMessage().isBlank()) {
            return fe.getDefaultMessage();
        }
        return fe.getCode() != null ? fe.getCode() : "Valor inválido";
    }
}
