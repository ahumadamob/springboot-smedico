package com.imb2025.smedico.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ConsultaRequestDto {
	@NotNull(message = "La fecha es obligatoria")
	@PastOrPresent(message = "La fecha no puede ser futura")
	private LocalDate fecha;

	@NotNull(message = "El turno es obligatorio")
	@Positive(message = "El id de turno debe ser positivo")
	private Long turnoId;

	@Min(value = 1, message = "La duración debe ser al menos 1 minuto")
	@Max(value = 480, message = "La duración no puede superar 480 minutos")
	private int duracionMin;

	@Size(max = 500, message = "Los comentarios no pueden superar los 500 caracteres")
	private String comentarios;
	
	@NotNull(message = "La descripcion es obligatoria")
	private String descripcionCorta;

	public ConsultaRequestDto() {
	}

	public ConsultaRequestDto(LocalDate fecha, Long turnoId, int duracionMin, String comentarios,
			String descripcionCorta) {
		this.fecha = fecha;
		this.turnoId = turnoId;
		this.duracionMin = duracionMin;
		this.comentarios = comentarios;
		this.descripcionCorta = descripcionCorta;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Long getTurnoId() {
		return turnoId;
	}

	public void setTurnoId(Long turnoId) {
		this.turnoId = turnoId;
	}

	public int getDuracionMin() {
		return duracionMin;
	}

	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
}
