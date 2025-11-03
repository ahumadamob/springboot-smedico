package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

// Extiende de BaseEntity para heredar id, version, createdAt, updatedAt
@Entity
public class EstadoTurno extends BaseEntity { 
    
    private String nombre;
    
    // Se elimina el campo 'esFinal' del Ejercicio 1.

    @OneToMany(mappedBy = "estadoTurno")
    private List<Turno> turnos;

    public EstadoTurno() {}

    // Constructor sin campos heredados
    public EstadoTurno(String nombre, List<Turno> turnos) {
        this.nombre = nombre;
        this.turnos = turnos;
    }
    
    // --- Getters específicos ---
    public String getNombre() {
        return nombre;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    // --- Setters específicos ---
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}