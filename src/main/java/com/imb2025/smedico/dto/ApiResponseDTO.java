package com.imb2025.smedico.dto;

import java.time.LocalDateTime;

public class ApiResponseDTO<T> {

    private boolean success;
    private T data;
    private String message;
    private LocalDateTime timestamp;

    public ApiResponseDTO() {
        this.timestamp = LocalDateTime.now();
    }

    // Getters y setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // No necesita setTimestamp porque se inicializa automáticamente
}
