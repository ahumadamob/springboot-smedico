package com.imb2025.smedico.dto.response;

import java.time.LocalDate;

public class DiagnosticoResponseDto {

    private Long id;
    private Long consultaId;
    private String descripcion;
    private LocalDate fechaDiagnostico;
    private Long version;

    public DiagnosticoResponseDto() { }

    public DiagnosticoResponseDto(Long id, Long consultaId, String descripcion, LocalDate fechaDiagnostico, Long version) {
        this.id = id;
        this.consultaId = consultaId;
        this.descripcion = descripcion;
        this.fechaDiagnostico = fechaDiagnostico;
        this.version = version;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaDiagnostico() { return fechaDiagnostico; }
    public void setFechaDiagnostico(LocalDate fechaDiagnostico) { this.fechaDiagnostico = fechaDiagnostico; }

    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}
