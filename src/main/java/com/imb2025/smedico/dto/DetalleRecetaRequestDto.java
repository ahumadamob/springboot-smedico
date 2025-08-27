package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class DetalleRecetaRequestDto {

    @NotNull(message = "El ID de la receta es obligatorio")
    @Positive(message = "El ID de la receta debe ser un número positivo")
    private Long recetaId;

    @NotNull(message = "El ID del medicamento es obligatorio")
    @Positive(message = "El ID del medicamento debe ser un número positivo")
    private Long medicamentoId;

    @NotBlank(message = "La dosis es obligatoria")
    @Size(max = 50, message = "La dosis no puede superar los 50 caracteres")
    private String dosis;

    @NotBlank(message = "La frecuencia es obligatoria")
    @Size(max = 50, message = "La frecuencia no puede superar los 50 caracteres")
    private String frecuencia;

    public DetalleRecetaRequestDto() {}

    public DetalleRecetaRequestDto(Long recetaId, Long medicamentoId, String dosis, String frecuencia) {
        this.recetaId = recetaId;
        this.medicamentoId = medicamentoId;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
    }

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


