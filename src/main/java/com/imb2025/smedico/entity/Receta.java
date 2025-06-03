package com.imb2025.smedico.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Receta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate fecha;
	private String observaciones;
	@ManyToOne
	private Medico medico;
	@ManyToOne
	private Paciente paciente;
	
	public Receta() {
	}
	
	public Receta(LocalDate fecha, String observaciones, Medico medico, Paciente paciente) {
	    this.fecha = fecha;
	    this.observaciones = observaciones;
	    this.medico = medico;
	    this.paciente = paciente;
	}
	


	public Long getId() {
		return id;
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
	public Medico getMedico() {
	    return medico;
	}

	public void setMedico(Medico medico) {
	    this.medico = medico;
	}

	public Paciente getPaciente() {
	    return paciente;
	}

	public void setPaciente(Paciente paciente) {
	    this.paciente = paciente;
	}

	public void setId(Long id) {
		this.id= id;		
	}

	

}
