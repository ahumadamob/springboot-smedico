package com.imb2025.smedico.dto.request; //m cambio de paquete
/*
import com.imb2025.smedico.entity.MedioPago.TipoPago;  
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;    
import jakarta.validation.constraints.Size;
*/

import com.imb2025.smedico.entity.MedioPago.TipoPago;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MedioPagoRequestDto {

	@NotBlank(message= "El campo 'nombre' se encuentra vacio, debe completarlo")
	@Size(min=3,max=35, message="longitud de campo fuera del rango (de 3 a 35 caracteres)")
	private String nombre;
	@NotNull (message= "Completar campo tipo con: TARJETA_CREDITO_DEBITO, MERCADO_PAGO o CANJE_CUPON")
	//@Size(min=11,max=22, message="longitud de campo fuera del rango (de 3 a 35 caracteres)") ERROR
	private TipoPago tipo;
	
	
	public MedioPagoRequestDto() {
	}
	
	public MedioPagoRequestDto(String nombre, TipoPago tipo) {
		super();
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
	
	
	
	


	
	
	
	
	
	/*	@NotBlank (message = "El campo del nombre del medio de pago No puede estar vacio")
		@Size (min = 3, max = 30, message = "El nombre del medio de pago debe tener entre 3 y 30 caracteres")
	    private String nombre;
		
		@NotNull (message = "El campo nombre del tipo de medio de pago No puede estar vacio")  
	    private TipoPago tipo; 
	 
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
	}*/



}
