package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version; // Necesario para @Version (TP08)
import java.util.List;

@Entity
public class EstadoTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // CAMBIO TP08: Control de Concurrencia Optimista
    @Version 
    private Integer version;
    
    private String nombre;
    
    // CAMBIO EJERCICIO 1: Atributo booleano
    private Boolean esFinal; 

    @OneToMany(mappedBy = "estadoTurno")
    private List<Turno> turnos;

    public EstadoTurno() {}

    // Constructor actualizado para incluir todos los campos
    public EstadoTurno(Long id, String nombre, Boolean esFinal, List<Turno> turnos) {
        this.id = id;
        this.nombre = nombre;
        this.esFinal = esFinal; 
        this.turnos = turnos;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }
    
    public Integer getVersion() { // Necesario para TP08 (Response DTO)
        return version;
    }

    public String getNombre() {
        return nombre;
    }
    
    public Boolean getEsFinal() { // Booleano de parcial ejercicio 1
        return esFinal;
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
    
    public void setEsFinal(Boolean esFinal) {
        this.esFinal = esFinal;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }
}
