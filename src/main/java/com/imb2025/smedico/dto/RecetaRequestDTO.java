package com.imb2025.smedico.dto;

import java.time.LocalDate;

public class RecetaRequestDTO {
	private LocalDate fecha;
	private String observaciones;
	private long medicoId;
	private long pacienteId;
	
	public RecetaRequestDTO(LocalDate fecha, String observaciones, long medicoId, long pacienteId) {
		super();
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
	public long getMedicoId() {
		return medicoId;
	}
	public void setMedicoId(long medicoId) {
		this.medicoId = medicoId;
	}
	public long getPacienteId() {
		return pacienteId;
	}
	public void setPacienteId(long pacienteId) {
		this.pacienteId = pacienteId;
	}
	

}
