package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EstadoTurnoRequestDto {
	
    private Long id;
    private Integer version; // NUEVO: Campo para control de concurrencia

    // Validaciones del tp 05
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", 
              message = "El nombre solo puede contener letras y espacios")
    private String nombre;

    public EstadoTurnoRequestDto() {
    }

    public EstadoTurnoRequestDto(String nombre) {
        this.nombre = nombre;
    }

    public EstadoTurnoRequestDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public EstadoTurnoRequestDto(Long id, String nombre, Integer version) {
        this.id = id;
        this.nombre = nombre;
        this.version = version; // Constructor con version para mapeo interno
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }
    
    public Integer getVersion() { // ¡MÉTODO AÑADIDO PARA RESOLVER EL ERROR!
        return version;
    }
    
    public String getNombre() {
        return nombre;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setVersion(Integer version) { // Setter para deserialización
        this.version = version;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
