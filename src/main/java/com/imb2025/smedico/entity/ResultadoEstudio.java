package com.imb2025.smedico.entity;

import java.time.LocalTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ResultadoEstudio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long ordenEstudioID;
	private long resultado;
	private LocalTime fechaCarga;
	private String observaciones;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrdenEstudioID() {
		return ordenEstudioID;
	}
	public void setOrdenEstudioID(long ordenEstudioID) {
		this.ordenEstudioID = ordenEstudioID;
	}
	public long getResultado() {
		return resultado;
	}
	public void setResultado(long resultado) {
		this.resultado = resultado;
	}
	public LocalTime getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(LocalTime fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
}
