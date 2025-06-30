package com.imb2025.smedico.dto;

import java.time.LocalTime;

public class ResultadoEstudioRequestDTO {

	
	private long ordenEstudioId;
	private long resultado;
	private LocalTime fechaCarga;
	private String observaciones;


	
	
	

	public ResultadoEstudioRequestDTO(long ordenEstudioId, long resultado, LocalTime fechaCarga, String observaciones) {
		super();
		this.ordenEstudioId = ordenEstudioId;
		this.resultado = resultado;
		this.fechaCarga = fechaCarga;
		this.observaciones = observaciones;
	}

	public long getOrdenEstudioId() {
		return ordenEstudioId;
	}

	public void setOrdenEstudioId(long ordenEstudioID) {
		this.ordenEstudioId = ordenEstudioID;
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

