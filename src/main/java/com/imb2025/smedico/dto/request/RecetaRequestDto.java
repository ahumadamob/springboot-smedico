package com.imb2025.smedico.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imb2025.smedico.entity.Receta.Estado;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class RecetaRequestDto {
	@NotNull(message = "La fecha es obligatoria")
	@PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha;
	@Size(min = 10, max = 500, message = "Las observaciones deben tener entre 10 y 500 caracteres")
	private String observaciones;
    @NotNull(message = "El médico es obligatorio")
    private Long medicoId;
    @NotNull(message = "El paciente es obligatorio")
    private Long pacienteId;
    @JsonProperty("estado")
    @NotNull(message = "El estado es obligatorio")
    private Estado estado;


    public RecetaRequestDto() {}

    public RecetaRequestDto(LocalDate fecha, String observaciones, Long medicoId, Long pacienteId, Estado estado) {
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
