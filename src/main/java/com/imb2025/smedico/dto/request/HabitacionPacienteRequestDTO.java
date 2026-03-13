package com.imb2025.smedico.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.imb2025.smedico.entity.HabitacionPaciente;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HabitacionPacienteRequestDTO {

    @NotNull(message = "El número de habitación no puede ser nulo")
    @Min(value = 1, message = "El número de habitación debe ser mayor a 0")
    private Integer numeroHabitacion;

    @NotNull(message = "El piso no puede ser nulo")
    private Integer version;
    
    @NotNull(message = "El piso no puede ser nulo")
    private Integer piso;

    @NotBlank(message = "El sector no puede estar vacío")
    @Size(min = 2, max = 50, message = "El sector debe tener entre 2 y 50 caracteres")
    private String sector;

    @NotNull(message = "Debe especificarse la cantidad de camas disponibles")
    @Min(value = 0, message = "La cantidad de camas no puede ser negativa")
    private Integer camasDisponibles;

    @Size(max = 200, message = "La descripción puede tener hasta 200 caracteres")
    private String descripcion;

    @NotBlank(message = "El identificador legible no puede estar vacío")
    private String identificadorLegible;

    @NotNull(message = "La fecha de vigencia no puede ser nula")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVigencia;

    private String observacionInterna;

    // Constructor vacío
    public HabitacionPacienteRequestDTO() {}

    // Getters y Setters

    public Integer getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

    public String getObservacionInterna() {
        return observacionInterna;
    }

    public void setObservacionInterna(String observacionInterna) {
        this.observacionInterna = observacionInterna;
    }

    // Conversión a entidad
    public HabitacionPaciente toEntity() {

        HabitacionPaciente entidad = new HabitacionPaciente();

        entidad.setNumeroHabitacion(this.numeroHabitacion);
        entidad.setPiso(this.piso);
        entidad.setSector(this.sector);
        entidad.setCamasDisponibles(this.camasDisponibles);
        entidad.setDescripcion(this.descripcion);
        entidad.setIdentificadorLegible(this.identificadorLegible);
        entidad.setFechaVigencia(this.fechaVigencia);
        entidad.setObservacionInterna(this.observacionInterna);
        
        return entidad;
    }
}
