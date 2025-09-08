package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para crear o actualizar DetalleReceta")
public class DetalleRecetaRequestDto {

    @NotNull(message = "recetaId no puede ser null")
    @Schema(description = "ID de la receta", example = "10", required = true)
    private Long recetaId;

    @NotNull(message = "medicamentoId no puede ser null")
    @Schema(description = "ID del medicamento", example = "5", required = true)
    private Long medicamentoId;

    @NotNull(message = "dosis no puede ser null")
    @Schema(description = "Cantidad de dosis", example = "2", required = true)
    private String dosis;

    @NotNull(message = "frecuencia no puede ser null")
    @Schema(description = "Frecuencia de administración", example = "3", required = true)
    private String frecuencia;

    // Constructores
    public DetalleRecetaRequestDto() {}

    public DetalleRecetaRequestDto(Long recetaId, Long medicamentoId, String dosis, String frecuencia) {
        this.recetaId = recetaId;
        this.medicamentoId = medicamentoId;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
    }

    // Getters y Setters
    public Long getRecetaId() { return recetaId; }
    public void setRecetaId(Long recetaId) { this.recetaId = recetaId; }

    public Long getMedicamentoId() { return medicamentoId; }
    public void setMedicamentoId(Long medicamentoId) { this.medicamentoId = medicamentoId; }

    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }
}
