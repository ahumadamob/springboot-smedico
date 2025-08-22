package com.imb2025.smedico.exception;

import java.time.LocalDateTime;

public class ApiErrorDTO {

    private boolean success = false;
    private String message;
    private LocalDateTime timestamp;

    public ApiErrorDTO(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y setters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
