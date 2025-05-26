package com.imb2025.smedico.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HistorialPaciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Long pacienteId;
    private String evento;
    private LocalDate fecha;
    private String observacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
