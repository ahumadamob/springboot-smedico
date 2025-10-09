package com.imb2025.smedico.dto.response;

public class DetalleRecetaResponseDto {

    private Long id;
    private Long recetaId;
    private Long medicamentoId;
    private String dosis;
    private String frecuencia;
    private Integer version;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRecetaId() { return recetaId; }
    public void setRecetaId(Long recetaId) { this.recetaId = recetaId; }

    public Long getMedicamentoId() { return medicamentoId; }
    public void setMedicamentoId(Long medicamentoId) { this.medicamentoId = medicamentoId; }

    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }
}
