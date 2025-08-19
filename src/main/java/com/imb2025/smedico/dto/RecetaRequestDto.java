package com.imb2025.smedico.dto;

import java.time.LocalDate;

public class RecetaRequestDto {
    private LocalDate fecha;
    private String observaciones;
    private Long medicoId;
    private Long pacienteId;

    public RecetaRequestDto() {}

    public RecetaRequestDto(LocalDate fecha, String observaciones, Long medicoId, Long pacienteId) {
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}
