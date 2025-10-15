package com.imb2025.smedico.dto.request;

import java.time.LocalDate;



import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class OrdenEstudioRequestDto {
	
    @FutureOrPresent(message = "La fecha debe ser hoy o una fecha futura")
    private LocalDate fecha;
    
    @NotNull(message="El campo medicoId es obligatorio")
    private Long medicoId;

    @NotNull(message="El campo pacienteId es obligatorio")
    private Long pacienteId;

    @NotNull(message="El campo estudioId es obligatorio")
    private Long estudioId;

    public OrdenEstudioRequestDto() {}

    public OrdenEstudioRequestDto(LocalDate fecha, Long medicoId, Long pacienteId, Long estudioId) {
        this.fecha = fecha;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
        this.estudioId = estudioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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

    public Long getEstudioId() {
        return estudioId;
    }

    public void setEstudioId(Long estudioId) {
        this.estudioId = estudioId;
    }
}
