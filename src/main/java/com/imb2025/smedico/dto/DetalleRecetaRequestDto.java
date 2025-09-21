package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DetalleRecetaRequestDto {

    @NotNull(message = "El ID de la receta no puede ser nulo")
    @Positive(message = "El ID de la receta debe ser positivo")
    private Long recetaId;

    @NotNull(message = "El ID del medicamento no puede ser nulo")
    @Positive(message = "El ID del medicamento debe ser positivo")
    private Long medicamentoId;

    @NotNull(message = "La dosis no puede ser nula")
    private String dosis;

    @NotNull(message = "La frecuencia no puede ser nula")
    private String frecuencia;

    // Getters y Setters
    public Long getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Long recetaId) {
        this.recetaId = recetaId;
    }

    public Long getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }
}


