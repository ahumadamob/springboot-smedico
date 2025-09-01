package com.imb2025.smedico.dto;

import com.imb2025.smedico.entity.MedioPago.TipoPago;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MedioPagoRequestDto {


  private String nombre;
  private String tipo;

public MedioPagoRequestDto() {}

public MedioPagoRequestDto(String nombre, String tipo) {
    this.nombre = nombre;
    this.tipo = tipo;
}

public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}

}
