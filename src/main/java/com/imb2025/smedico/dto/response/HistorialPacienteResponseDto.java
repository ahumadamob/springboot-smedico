package com.imb2025.smedico.dto.response;

import java.time.LocalDate;

public class HistorialPacienteResponseDto {

    private Long id;
    private Long pacienteId;
    private String evento;
    private LocalDate fecha;
    private String observacion;
    private Integer version;

    public HistorialPacienteResponseDto() {}

    public HistorialPacienteResponseDto(Long id, Long pacienteId, String evento, LocalDate fecha, String observacion, Integer version) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.evento = evento;
        this.fecha = fecha;
        this.observacion = observacion;
        this.version = version;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }
    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getEvento() {
        return evento;
    }
    public void setEvento(String evento) {
        this.evento = evento;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
}
