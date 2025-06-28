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
	private Long medicoId;
	private Long pacienteId;
	private String observaciones;
	
	
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
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public void setId(Long recetaId) {
		// TODO Auto-generated method stub
		
	}
	

}
