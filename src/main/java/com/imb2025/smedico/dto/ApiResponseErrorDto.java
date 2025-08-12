package com.imb2025.smedico.dto;

import java.time.Instant;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para respuestas de error estructuradas en la API.
 */
@Schema(description = "Respuesta de error con detalles por campo")
public class ApiResponseErrorDto {

    @Schema(description = "Siempre es false en caso de error")
    private boolean success;

    @Schema(description = "Lista de errores por campo")
    private List<FieldErrorDto> errors;

    @Schema(description = "Marca de tiempo del error")
    private Instant timestamp;

    public ApiResponseErrorDto() {
        this.timestamp = Instant.now();
    }

    public ApiResponseErrorDto(boolean success, List<FieldErrorDto> errors) {
        this.success = success;
        this.errors = errors;
        this.timestamp = Instant.now();
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public List<FieldErrorDto> getErrors() {
        return errors;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrors(List<FieldErrorDto> errors) {
        this.errors = errors;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
