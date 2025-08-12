package com.imb2025.smedico.dto;

import java.time.LocalDate;

public class ConsultaRequestDto {
    
	private LocalDate fecha;
    private Long turnoId;
    private int duracionMin;
    private String comentarios;
    
	public ConsultaRequestDto() {		
	}

	public ConsultaRequestDto(LocalDate fecha, Long turnoId, int duracionMin, String comentarios) {
		super();
		this.fecha = fecha;
		this.turnoId = turnoId;
		this.duracionMin = duracionMin;
		this.comentarios = comentarios;
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
