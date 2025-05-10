package com.imb2025.smedico.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Receta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate fecha;
	private Long MedicoId;
	private Long PacienteId;
	private String Observaciones;
	
	
	public Long getId() {
		return id;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public Long getMedicoId() {
		return MedicoId;
	}
	public void setMedicoId(Long medicoId) {
		MedicoId = medicoId;
	}
	public Long getPacienteId() {
		return PacienteId;
	}
	public void setPacienteId(Long pacienteId) {
		PacienteId = pacienteId;
	}
	public String getObservaciones() {
		return Observaciones;
	}
	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	

}
