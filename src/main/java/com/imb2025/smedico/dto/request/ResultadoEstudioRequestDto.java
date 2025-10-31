package com.imb2025.smedico.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


public class ResultadoEstudioRequestDto {

	@NotNull(message = "La orden de estudio es obligatoria")
	@Positive(message = "El id de la ordenEstudio debe ser positivo")
	private Long ordenEstudioId;
	@NotNull(message = "El estudio es obligatorio")
	@Positive(message = "El id del estudio debe ser positivo")
	private Long estudioId;
	@NotNull(message = "El resultado es obligatorio")
	private Long resultado;

    @NotNull(message = "La hora es obligatoria")
    @PastOrPresent(message = "La hora no puede ser futura")
    private LocalDate fechaCarga;
	@Size(min = 5, message = "Las observaciones no pueden tener menos de 5 caracteres")
    private String observaciones;

        public ResultadoEstudioRequestDto() {}

        public ResultadoEstudioRequestDto(
                long ordenEstudioId, long estudioId, long resultado, LocalDate fechaCarga, String observaciones) {
                this.ordenEstudioId = ordenEstudioId;
                this.estudioId = estudioId;
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

        

        public LocalDate getFechaCarga() {
                return fechaCarga;
        }

        public void setFechaCarga(LocalDate fechaCarga) {
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

