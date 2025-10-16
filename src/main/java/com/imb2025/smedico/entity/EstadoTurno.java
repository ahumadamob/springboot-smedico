package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version; // Importación necesaria para @Version
import java.util.List;

@Entity
public class EstadoTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Campo para el Control de Concurrencia (Requisito TP08)
    @Version 
    private Integer version;
    
    private String nombre;

    @OneToMany(mappedBy = "estadoTurno")
    private List<Turno> turnos;

    public EstadoTurno() {}

    public EstadoTurno(Long id, String nombre, List<Turno> turnos) {
        this.id = id;
        this.nombre = nombre;
        this.turnos = turnos;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }

    /**
     * Getter del campo de concurrencia. JPA lo usa para validar que el registro
     * no haya sido modificado por otro usuario antes de actualizarlo.
     */
    public Integer getVersion() { 
        return version;
    }
    
    public String getNombre() {
        return nombre;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
