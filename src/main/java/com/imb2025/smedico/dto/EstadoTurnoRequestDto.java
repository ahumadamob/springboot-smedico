package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EstadoTurnoRequestDto {
	
    // Atributo para el ID (usado en GET, PUT, y POST de respuesta)
    private Long id;
    
    // Validaciones del tp 05
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", 
              message = "El nombre solo puede contener letras y espacios")
    private String nombre;

    public EstadoTurnoRequestDto() {
    }

    // Constructor para CREATE (POST) donde el ID aún no existe
    public EstadoTurnoRequestDto(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo (usado para GET y respuestas de CREATE/UPDATE)
    public EstadoTurnoRequestDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
