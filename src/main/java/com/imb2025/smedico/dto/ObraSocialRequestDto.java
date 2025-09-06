package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotBlank;

public class ObraSocialRequestDto {
	
 @NotBlank (message = "El nombre no puede estar vacio")
    private String nombre;
    
    private String telefono;
    
    @NotBlank (message = "La direccion no puede estar vacio")
    private String direccion;
    
    @NotBlank (message = "Debe colocar Cobertura/Plan")
    private String cobertura;

    public ObraSocialRequestDto() {}

    public ObraSocialRequestDto(
            String nombre, String telefono, String direccion, String cobertura) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cobertura = cobertura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }
}

