package com.imb2025.smedico.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorDTO {
    private boolean success = false;
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<FieldValidationError> errors;

    public ApiErrorDTO() {}

    // Constructor con mensaje + lista de errores
    public ApiErrorDTO(String message, List<FieldValidationError> errors) {
        this.message = message;
        this.errors = errors;
    }

    // Constructor solo con mensaje (para 404, 500, etc.)
    public ApiErrorDTO(String message) {
        this.message = message;
        this.errors = null;
    }

    // Getters y setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public List<FieldValidationError> getErrors() { return errors; }
    public void setErrors(List<FieldValidationError> errors) { this.errors = errors; }
}


