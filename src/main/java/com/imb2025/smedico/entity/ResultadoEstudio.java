package com.imb2025.smedico.entity;

import java.time.LocalTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class ResultadoEstudio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "ordenEstudioID")
	private OrdenEstudio ordenEstudio;
	
	private Estudio estudio;
	private LocalTime fechaCarga;
	private String observaciones;

	
	public ResultadoEstudio() {
		super();
	}

	public ResultadoEstudio(OrdenEstudio ordenEstudio, Estudio estudio, LocalTime fechaCarga, String observaciones) {
	    this.ordenEstudio = ordenEstudio;
	    this.estudio = estudio;
	    this.fechaCarga = fechaCarga;
	    this.observaciones = observaciones;
	}




	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	public OrdenEstudio getOrdenEstudio() {
		return ordenEstudio;
	}

	
	public void setOrdenEstudio(OrdenEstudio ordenEstudio) {
		this.ordenEstudio = ordenEstudio;
	}

	
	public Estudio getEstudio() {
		return estudio;
	}
	
	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
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
