package com.imb2025.smedico.dto.response;

import com.imb2025.smedico.entity.MedioPago.Estado;
import com.imb2025.smedico.entity.MedioPago.TipoPago;

public class MedioPagoResponseDto {
	
	private Long id;
    private Long version;
	private String nombre;
	private Estado estado; //a
	private TipoPago tipo;
    

    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

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
	//a
	public Estado getEstado() {
		return estado;
	}
	//a
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
    
}
