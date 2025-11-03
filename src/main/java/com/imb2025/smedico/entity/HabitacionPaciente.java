package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "habitaciones_paciente")
public class HabitacionPaciente extends BaseEntity {

    private String sector;
    private Integer numero;
    private String estado;
    private Integer cantidadCamas;

    public HabitacionPaciente() {}

    public HabitacionPaciente(String sector, Integer numero, String estado, Integer cantidadCamas) {
        this.sector = sector;
        this.numero = numero;
        this.estado = estado;
        this.cantidadCamas = cantidadCamas;
    }

    // Getters y Setters
    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getCantidadCamas() { return cantidadCamas; }
    public void setCantidadCamas(Integer cantidadCamas) { this.cantidadCamas = cantidadCamas; }

	public void setNumeroHabitacion(
			@NotNull(message = "El número de habitación no puede ser nulo") @Min(value = 1, message = "El número de habitación debe ser mayor a 0") Integer numeroHabitacion) {
		// TODO Auto-generated method stub
		
	}

	public void setPiso(@NotNull(message = "El piso no puede ser nulo") Integer piso) {
		// TODO Auto-generated method stub
		
	}

	public void setCamasDisponibles(
			@NotNull(message = "Debe especificarse la cantidad de camas disponibles") @Min(value = 0, message = "La cantidad de camas no puede ser negativa") Integer camasDisponibles) {
		// TODO Auto-generated method stub
		
	}

	public void setDescripcion(
			@Size(max = 200, message = "La descripción puede tener hasta 200 caracteres") String descripcion) {
		// TODO Auto-generated method stub
		
	}
}

