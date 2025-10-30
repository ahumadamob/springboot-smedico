package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

// CORRECCIÓN: Extiende de BaseEntity para heredar id, version, createdAt, updatedAt
@Entity
public class EstadoTurno extends BaseEntity { 
    // Se eliminan los campos id y version, que ahora están en BaseEntity
    
    private String nombre;
    
    // CAMBIO EJERCICIO 1: Atributo booleano
    private Boolean esFinal; 

    @OneToMany(mappedBy = "estadoTurno")
    private List<Turno> turnos;

    public EstadoTurno() {}

    // Constructor sin id, version (heredados)
    public EstadoTurno(String nombre, Boolean esFinal, List<Turno> turnos) {
        this.nombre = nombre;
        this.esFinal = esFinal; 
        this.turnos = turnos;
    }
    
    // El método getId() es heredado de BaseEntity
    // El método getVersion() es heredado de BaseEntity
    
    // --- Getters específicos ---
    public String getNombre() {
        return nombre;
    }
    
    public Boolean getEsFinal() { 
        return esFinal;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    // --- Setters específicos ---
    // El setId() y setVersion() son heredados
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEsFinal(Boolean esFinal) {
        this.esFinal = esFinal;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
