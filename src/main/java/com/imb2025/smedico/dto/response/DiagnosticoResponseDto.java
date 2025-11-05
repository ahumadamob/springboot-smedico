package com.imb2025.smedico.dto.response;

import java.time.LocalDate;

public class DiagnosticoResponseDto {

    private Long id;
    private Long consultaId;
    private String descripcion;
    private LocalDate fechaDiagnostico;

    private String identificadorLegible; // Ej.2
    private LocalDate fechaVigencia;     // Ej.3

    private Long version; // desde BaseEntity

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDate getFechaDiagnostico() { return fechaDiagnostico; }
    public void setFechaDiagnostico(LocalDate fechaDiagnostico) { this.fechaDiagnostico = fechaDiagnostico; }
    public String getIdentificadorLegible() { return identificadorLegible; }
    public void setIdentificadorLegible(String identificadorLegible) { this.identificadorLegible = identificadorLegible; }
    public LocalDate getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(LocalDate fechaVigencia) { this.fechaVigencia = fechaVigencia; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}
