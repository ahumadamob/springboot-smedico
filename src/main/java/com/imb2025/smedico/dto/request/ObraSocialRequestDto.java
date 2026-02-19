package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ObraSocialRequestDto {

   
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
    @Pattern(regexp = "^[0-9+\\-() ]*$", message = "El teléfono solo puede contener números y símbolos válidos (+ - ( ) espacio)")
    private String telefono;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 150, message = "La dirección no puede superar los 150 caracteres")
    private String direccion;

    @NotBlank(message = "Debe especificar la cobertura o plan")
    @Size(max = 100, message = "La cobertura no puede superar los 100 caracteres")
    private String cobertura;

    public ObraSocialRequestDto() {}

    public ObraSocialRequestDto(String nombre, String telefono, String direccion, String cobertura) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cobertura = cobertura;
    }

    // Getters y Setters
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

