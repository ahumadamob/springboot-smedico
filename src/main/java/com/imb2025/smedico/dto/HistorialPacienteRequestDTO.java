package com.imb2025.smedico.dto;

import java.time.LocalDate;

public class HistorialPacienteRequestDTO {
	private Long pacienteId;
	private String evento;
	private LocalDate fecha;
	private String observacion;
	
	public HistorialPacienteRequestDTO() {}
	
	public HistorialPacienteRequestDTO(Long pacienteId, String evento,LocalDate fecha,String observacion) {
		super();
		this.pacienteId = pacienteId;
		this.evento = evento;
		this.fecha = fecha;
		this.observacion = observacion;
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
	
}
