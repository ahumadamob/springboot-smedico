package com.imb2025.smedico.dto;
import java.time.Instant;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO estándar para respuestas exitosas de la API.
 */
@Schema(description = "Respuesta exitosa de la API")
public class ApiResponseSuccessDto<T> {

    @Schema(description = "Indica si la operación fue exitosa")
    private boolean success;

    @Schema(description = "Mensaje descriptivo de la operación")
    private String message;

    @Schema(description = "Datos devueltos por la operación")
    private T data;

    @Schema(description = "Marca de tiempo en que se generó la respuesta")
    private Instant timestamp;

    public ApiResponseSuccessDto() {
        this.timestamp = Instant.now();
    }

    public ApiResponseSuccessDto(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now();
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
