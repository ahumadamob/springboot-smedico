package com.imb2025.smedico.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class ResultadoEstudioRequestDto {

	@NotBlank(message = "La orden de estudio es obligatoria")
	@Positive(message = "El id de la ordenEstudio debe ser positivo")
	private long ordenEstudioId;
	@NotBlank(message = "El estudio es obligatorio")
	@Positive(message = "El id del estudio debe ser positivo")
	private long estudioId;
	@NotBlank(message = "El resultado es obligatorio")
	private long resultado;
	@NotBlank(message = "La fecha es obligatoria")
	@PastOrPresent(message = "La fecha no puede ser futura")
	private LocalTime fechaCarga;
	@Size(min = 5, message = "Las observaciones no pueden tener menos de 5 caracteres")
    private String observaciones;

        public ResultadoEstudioRequestDto() {}

        public ResultadoEstudioRequestDto(
                long ordenEstudioId, long resultado, LocalTime fechaCarga, String observaciones) {
                this.ordenEstudioId = ordenEstudioId;
                this.resultado = resultado;
                this.fechaCarga = fechaCarga;
                this.observaciones = observaciones;
        }

	public long getOrdenEstudioId() {
		return ordenEstudioId;
	}

	public void setOrdenEstudioId(long ordenEstudioID) {
		this.ordenEstudioId = ordenEstudioID;
	}

	public long getResultado() {
		return resultado;
	}

	public void setResultado(long resultado) {
		this.resultado = resultado;
	}

	public LocalTime getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(LocalTime fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public long getEstudioId() {
		return estudioId;
	}

	public void setEstudioId(long estudioId) {
		this.estudioId = estudioId;
	}
	
}

