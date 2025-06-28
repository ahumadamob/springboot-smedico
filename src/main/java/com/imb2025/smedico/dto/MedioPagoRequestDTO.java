package com.imb2025.smedico.dto;

import com.imb2025.smedico.entity.MedioPago.TipoPago;

public class MedioPagoRequestDTO {
	
	private String nombre;
    private TipoPago tipo;
    
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoPago getTipo() {
		return tipo;
	}
	public void setTipo(TipoPago tipo) {
		this.tipo = tipo;
	}
	
    
	
}
