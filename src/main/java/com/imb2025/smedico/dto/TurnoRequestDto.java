package com.imb2025.smedico.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public class TurnoRequestDto {
	@NotNull(message = "La fecha del turno es obligatoria")
    @Future(message = "La fecha debe ser futura")
    private LocalDate fecha;

    @NotNull(message = "La hora del turno es obligatoria")
    private LocalTime hora;

    @NotNull(message = "Debe especificar el paciente")
    @Positive(message = "El ID del paciente no puede ser menor o igual a cero")
    private Long pacienteId;

    @NotNull(message = "Debe especificar el médico")
    @Positive(message = "El ID del médico no puede ser menor o igual a cero")
    private Long medicoId;

    @NotNull(message = "Debe especificar el estado del turno")
    @Positive(message = "El ID del estado no puede ser menor o igual a cero")
    private Long estadoTurnoId;

    public TurnoRequestDto() {}

    public TurnoRequestDto(
            LocalDate fecha, LocalTime hora, Long pacienteId, Long medicoId, Long estadoTurnoId) {
        this.fecha = fecha;
        this.hora = hora;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.estadoTurnoId = estadoTurnoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getEstadoTurnoId() {
        return estadoTurnoId;
    }

    public void setEstadoTurnoId(Long estadoTurnoId) {
        this.estadoTurnoId = estadoTurnoId;
    }
}

