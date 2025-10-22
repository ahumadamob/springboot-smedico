package com.imb2025.smedico.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class HistorialPacienteRequestDto {
	
	@NotNull(message = "El pacienteId es OBLIGATORIO")
	@Positive(message = "El pacienteId debe ser un NÚMERO POSITIVO")
    private Long pacienteId;
	
	@NotBlank(message = "El evento es OBLIGATORIO")
	@Size(min = 3, max = 100, message = "El evento debe tener entre 3 y 100 caracteres")
    private String evento;
	
	@NotNull(message = "La fecha es OBLIGATORIA")
	@PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha;
	
	@Size(max = 255, message = "La observacion no puede superar los 255 caracteres")
    private String observacion;
	
	@NotNull(message = "La fecha de vigencia es OBLIGATORIA")
    private LocalDate fechaVigencia;

    public HistorialPacienteRequestDto() {}

    public HistorialPacienteRequestDto(
            Long pacienteId, String evento, LocalDate fecha, String observacion, LocalDate fechaVigencia) {
        this.pacienteId = pacienteId;
        this.evento = evento;
        this.fecha = fecha;
        this.observacion = observacion;
        this.fechaVigencia = fechaVigencia;
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

    public LocalDate getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(LocalDate fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }
}
