package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "habitaciones_paciente")
public class HabitacionPaciente extends BaseEntity {

    private Integer numeroHabitacion;
    private Integer piso;
    private String sector;
    private Integer camasDisponibles;
    private String descripcion;

    public HabitacionPaciente() {}

	public HabitacionPaciente(Integer numeroHabitacion, Integer piso, String sector, Integer camasDisponibles,
			String descripcion) {
		super();
		this.numeroHabitacion = numeroHabitacion;
		this.piso = piso;
		this.sector = sector;
		this.camasDisponibles = camasDisponibles;
		this.descripcion = descripcion;
	}

	public Integer getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(Integer numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Integer getCamasDisponibles() {
		return camasDisponibles;
	}

	public void setCamasDisponibles(Integer camasDisponibles) {
		this.camasDisponibles = camasDisponibles;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    
    
}

