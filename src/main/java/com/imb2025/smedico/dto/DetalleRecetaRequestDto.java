package com.imb2025.smedico.dto;

public class DetalleRecetaRequestDto {

    private Long recetaId;
    private Long medicamentoId;
    private String dosis;
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


