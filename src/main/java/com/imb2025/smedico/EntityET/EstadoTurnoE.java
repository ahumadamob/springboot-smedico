package com.imb2025.smedico.EntityET;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado_turno") // Especifica el nombre de la tabla en la BD
public class EstadoTurnoE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;
    private String nombre;

    // Constructor vacío
    public EstadoTurnoE() {}

    // Constructor con parámetros
    public EstadoTurnoE(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

