package com.imb2025.smedico.dto;

public class FieldValidationError {
    private String field;
    private String message;
    private Object rejectedValue;

    public FieldValidationError() {}

    // Constructor para validaciones con valor rechazado
    public FieldValidationError(String field, String message, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    // Constructor simple sin rejectedValue
    public FieldValidationError(String field, String message) {
        this.field = field;
        this.message = message;
        this.rejectedValue = null;
    }

    // Getters y setters
    public String getField() { return field; }
    public void setField(String field) { this.field = field; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Object getRejectedValue() { return rejectedValue; }
    public void setRejectedValue(Object rejectedValue) { this.rejectedValue = rejectedValue; }
}


