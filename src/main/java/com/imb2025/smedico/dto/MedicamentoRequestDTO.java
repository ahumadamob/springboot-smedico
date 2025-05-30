package com.imb2025.smedico.dto;

public class MedicamentoRequestDTO {
	private String nombre;
	private String presentacion;
	private String dosisSugerida;
	
	public MedicamentoRequestDTO(String nombre, String presentacion, String dosisSugerida) {
		super();
		this.nombre = nombre;
		this.presentacion = presentacion;
		this.dosisSugerida = dosisSugerida;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getDosisSugerida() {
		return dosisSugerida;
	}

	public void setDosisSugerida(String dosisSugerida) {
		this.dosisSugerida = dosisSugerida;
	}

	

}
