package com.imb2025.smedico.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ObraSocialRequestDto {

    @NotBlank(message = "El identificador legible no puede estar vacío.")
    @Size(max = 50, message = "El identificador no debe superar los 50 caracteres.")
    private String identificadorLegible;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no debe superar los 100 caracteres.")
    private String nombre;

    @NotBlank(message = "El teléfono no puede estar vacío.")
    private String telefono;

    @NotBlank(message = "La dirección no puede estar vacía.")
    private String direccion;

    @NotBlank(message = "La cobertura no puede estar vacía.")
    private String cobertura;
    
    @NotBlank(message = "fecha vacia o nula")
    private LocalDate fechaVigencia;


    // ✅ Getters y Setters
    public String getIdentificadorLegible() {
        return identificadorLegible;
    }

    public void setIdentificadorLegible(String identificadorLegible) {
        this.identificadorLegible = identificadorLegible;
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
    
    
    public LocalDate getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(LocalDate fechaVigencia) { this.fechaVigencia = fechaVigencia; }
}


