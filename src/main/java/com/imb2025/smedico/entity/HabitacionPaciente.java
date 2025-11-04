package com.imb2025.smedico.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "habitaciones_paciente")
public class HabitacionPaciente extends BaseEntity {

    private Integer numeroHabitacion;
    private Integer piso;
    private String sector;
    private Integer camasDisponibles;
    private String descripcion;

    private String identificadorLegible;
    private LocalDate fechaVigencia;
    
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

	public String getIdentificadorLegible() {
        return identificadorLegible;
    }

    public void setIdentificadorLegible(String identificadorLegible) {
        this.identificadorLegible = identificadorLegible;
    }

    public LocalDate getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(LocalDate fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }
}
    


