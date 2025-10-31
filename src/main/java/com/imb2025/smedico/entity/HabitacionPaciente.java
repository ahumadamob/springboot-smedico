package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "habitacion_paciente")
public class HabitacionPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroHabitacion;
    private Integer piso;
    private String sector;
    private Integer camasDisponibles;
    private String descripcion;

    // Constructor vacío (obligatorio para JPA)
    public HabitacionPaciente() {}

    // Constructor completo (sin ID, útil al crear nuevos registros)
    public HabitacionPaciente(Integer numeroHabitacion, Integer piso, String sector,
                              Integer camasDisponibles, String descripcion) {
        this.numeroHabitacion = numeroHabitacion;
        this.piso = piso;
        this.sector = sector;
        this.camasDisponibles = camasDisponibles;
        this.descripcion = descripcion;
    }

    // Constructor con ID (opcional, útil para test o actualizaciones)
    public HabitacionPaciente(Long id, Integer numeroHabitacion, Integer piso, String sector,
                              Integer camasDisponibles, String descripcion) {
        this.id = id;
        this.numeroHabitacion = numeroHabitacion;
        this.piso = piso;
        this.sector = sector;
        this.camasDisponibles = camasDisponibles;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNumeroHabitacion() { return numeroHabitacion; }
    public void setNumeroHabitacion(Integer numeroHabitacion) { this.numeroHabitacion = numeroHabitacion; }

    public Integer getPiso() { return piso; }
    public void setPiso(Integer piso) { this.piso = piso; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public Integer getCamasDisponibles() { return camasDisponibles; }
    public void setCamasDisponibles(Integer camasDisponibles) { this.camasDisponibles = camasDisponibles; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // Método toString 
    @Override
    public String toString() {
        return "HabitacionPaciente{" +
                "id=" + id +
                ", numeroHabitacion=" + numeroHabitacion +
                ", piso=" + piso +
                ", sector='" + sector + '\'' +
                ", camasDisponibles=" + camasDisponibles +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
