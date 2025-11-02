package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "habitacion_paciente")
public class HabitacionPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El número de habitación no puede ser nulo")
    @Min(value = 1, message = "El número de habitación debe ser mayor a 0")
    @Column(name = "numero_habitacion", nullable = false)
    private Integer numeroHabitacion;

    @NotNull(message = "El piso no puede ser nulo")
    @Column(nullable = false)
    private Integer piso;

    @NotBlank(message = "El sector no puede estar vacío")
    @Size(min = 2, max = 50, message = "El sector debe tener entre 2 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String sector;

    @NotNull(message = "Debe especificarse la cantidad de camas disponibles")
    @Min(value = 0, message = "La cantidad de camas no puede ser negativa")
    @Column(name = "camas_disponibles", nullable = false)
    private Integer camasDisponibles;

    @Size(max = 200, message = "La descripción puede tener hasta 200 caracteres")
    @Column(length = 200)
    private String descripcion;

    //  Versión para control de concurrencia optimista (opcional, pero recomendable)
    @Version
    private Integer version;

    //  Constructores
    public HabitacionPaciente() {}

    public HabitacionPaciente(Integer numeroHabitacion, Integer piso, String sector,
                              Integer camasDisponibles, String descripcion) {
        this.numeroHabitacion = numeroHabitacion;
        this.piso = piso;
        this.sector = sector;
        this.camasDisponibles = camasDisponibles;
        this.descripcion = descripcion;
    }

    public HabitacionPaciente(Long id, Integer numeroHabitacion, Integer piso, String sector,
                              Integer camasDisponibles, String descripcion, Integer version) {
        this.id = id;
        this.numeroHabitacion = numeroHabitacion;
        this.piso = piso;
        this.sector = sector;
        this.camasDisponibles = camasDisponibles;
        this.descripcion = descripcion;
        this.version = version;
    }

    //  Getters y Setters
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

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }

    //  equals y hashCode basados en id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HabitacionPaciente)) return false;
        HabitacionPaciente that = (HabitacionPaciente) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // 🔹 toString()
    @Override
    public String toString() {
        return "HabitacionPaciente{" +
                "id=" + id +
                ", numeroHabitacion=" + numeroHabitacion +
                ", piso=" + piso +
                ", sector='" + sector + '\'' +
                ", camasDisponibles=" + camasDisponibles +
                ", descripcion='" + descripcion + '\'' +
                ", version=" + version +
                '}';
    }
}
