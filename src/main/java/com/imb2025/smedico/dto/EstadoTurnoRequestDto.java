package com.imb2025.smedico.dto; // Corregido: Usar el package correcto

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EstadoTurnoRequestDto {
	
    // Atributo para el ID (usado en GET, PUT, y POST de respuesta)
    private Long id;
    
    // Campo para el control de concurrencia (TP08) - CORREGIDO: Tipo Integer
    private Integer version; 
    
    // Validaciones del tp 05
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", 
              message = "El nombre solo puede contener letras y espacios")
    private String nombre;
    
    // Campo del Ejercicio 1 (Filtro Booleano)
    private Boolean esFinal; 

    public EstadoTurnoRequestDto() {
    }

    // Constructor para CREATE (POST) donde el ID aún no existe
    public EstadoTurnoRequestDto(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo para GET y respuestas de CREATE/UPDATE (ID y Nombre)
    public EstadoTurnoRequestDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    // Constructor completo para mapeo de respuesta (cuatro parámetros) - CORREGIDO LA FIRMA
    public EstadoTurnoRequestDto(Long id, String nombre, Integer version, Boolean esFinal) {
        this.id = id;
        this.nombre = nombre;
        this.version = version;
        this.esFinal = esFinal;
    }


    // --- Getters ---
    public Long getId() { return id; }
    // CORREGIDO: El GetVersion debe ser Integer
    public Integer getVersion() { return version; } 
    public String getNombre() { return nombre; }
    public Boolean getEsFinal() { return esFinal; }

    // --- Setters ---
    public void setId(Long id) { this.id = id; }
    // CORREGIDO: El SetVersion debe ser Integer
    public void setVersion(Integer version) { this.version = version; } 
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEsFinal(Boolean esFinal) { this.esFinal = esFinal; }
}


