package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EstadoTurnoRequestDto {
	
    private Long id;
    private Long version; // CORREGIDO: De Integer a Long para coincidir con BaseEntity

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
    
    public EstadoTurnoRequestDto(Long id, String nombre, Long version) { // Constructor corregido
        this.id = id;
        this.nombre = nombre;
        this.version = version;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }
    
    public Long getVersion() { // Getter corregido
        return version;
    }
    
    public String getNombre() {
        return nombre;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setVersion(Long version) { // Setter corregido
        this.version = version;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
