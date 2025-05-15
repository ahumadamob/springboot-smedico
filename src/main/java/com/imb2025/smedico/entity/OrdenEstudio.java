package com.imb2025.smedico.entity;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class OrdenEstudio {

	// OrdenEstudio: id,fecha,medicoId,pacienteId, estudioId
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fecha;
	private Long medicoId;
	private Long pacienteId;	
	private Long estudioId;
	

	// Getters y Setters
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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
