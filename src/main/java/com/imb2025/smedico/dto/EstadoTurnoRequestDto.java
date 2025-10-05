package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EstadoTurnoRequestDto {
	
    // ID es necesario para el mapeo de respuesta (GET/PUT)
    private Long id; 

    // Validaciones del tp 05
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
             message = "El nombre solo puede contener letras y espacios")
    private String nombre;

    // Constructor vacío (necesario para Spring/Jackson)
    public EstadoTurnoRequestDto() {
    }

    // Constructor para Request (POST/Creación): solo nombre
    public EstadoTurnoRequestDto(String nombre) {
        this.nombre = nombre;
    }
    
    // Constructor para Response (GET/PUT): con ID
    public EstadoTurnoRequestDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
