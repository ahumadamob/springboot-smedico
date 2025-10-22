package com.imb2025.smedico.dto.request; //m cambio de paquete

import com.imb2025.smedico.entity.MedioPago.Estado;
import com.imb2025.smedico.entity.MedioPago.TipoPago;  
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;    
import jakarta.validation.constraints.Size;


public class MedioPagoRequestDto {
	
	@NotBlank (message = "El campo del nombre del medio de pago No puede estar vacio")
	@Size (min = 3, max = 30, message = "El nombre del medio de pago debe tener entre 3 y 30 caracteres")
    private String nombre;
	
	@NotNull (message = "El campo nombre del tipo de medio de pago No puede estar vacio")  
    private TipoPago tipo; 
	
	//a
	@NotNull(message = "El campo estado del medio de pago no puede estar vacío")
	private Estado estado;

 
	public MedioPagoRequestDto() {}

	public MedioPagoRequestDto(String nombre, TipoPago tipo) { 
	    this.nombre = nombre;
	    this.tipo = tipo;
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
