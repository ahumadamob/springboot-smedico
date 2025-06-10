package com.imb2025.smedico.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class OrdenEstudioRequestDTO {
   
	 
	
	private LocalDate fecha;	
	private Long medicoId;
	private Long pacienteId;
	private Long estudioId;
	
	public OrdenEstudioRequestDTO(LocalDate fecha,Long medicoId,Long pacienteId, Long estudioId) {
	    super();
		this.fecha=fecha;
        this.medicoId=medicoId;
        this.pacienteId=pacienteId;
        this.estudioId=estudioId;
	}
	
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha=fecha;
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
