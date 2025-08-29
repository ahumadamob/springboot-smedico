package com.imb2025.smedico.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para representar un error específico de un campo.
 */
@Schema(description = "Error de validación para un campo específico")
public class FieldErrorDto {

    @Schema(description = "Nombre del campo que tiene error")
    private String field;

    @Schema(description = "Mensaje descriptivo del error")
    private String message;

    public FieldErrorDto() {
    }

    public FieldErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    // Getters
    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    // Setters
    public void setField(String field) {
        this.field = field;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
