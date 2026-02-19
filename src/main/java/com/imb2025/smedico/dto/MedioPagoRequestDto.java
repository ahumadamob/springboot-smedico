package com.imb2025.smedico.dto;

import com.imb2025.smedico.entity.MedioPago.TipoPago;  //a   para poder usar el enum directamente

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;    //a   
import jakarta.validation.constraints.Size;


public class MedioPagoRequestDto {
	
	@NotBlank (message = "El campo del nombre del medio de pago No puede estar vacio")
	@Size (min = 3, max = 30, message = "El nombre del medio de pago debe tener entre 3 y 30 caracteres")
    private String nombre;
	
	@NotNull (message = "El campo nombre del tipo de medio de pago No puede estar vacio")  //m   antes notblack para validar string, ahora notnull pra validar enum
    private TipoPago tipo; //m   tipo de pago como dato tipo enum=TipoPago
 
public MedioPagoRequestDto() {}

public MedioPagoRequestDto(String nombre, TipoPago tipo) {  //m     tipo modificado en el constructor como enum 
    this.nombre = nombre;
    this.tipo = tipo;
}

public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public TipoPago getTipo() {      //m    ahora tipo enum
	return tipo;
}
public void setTipo(TipoPago tipo) {    //m    ahora tipo enum
	this.tipo = tipo;
}



}
