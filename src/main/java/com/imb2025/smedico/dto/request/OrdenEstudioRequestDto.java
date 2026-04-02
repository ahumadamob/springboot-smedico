package com.imb2025.smedico.dto.request;

import java.time.LocalDate;



import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import utilities.EstadoOrden;

public class OrdenEstudioRequestDto {
	
    @FutureOrPresent(message = "La fecha debe ser hoy o una fecha futura")
    private LocalDate fecha;
    
    @NotNull(message="El campo medicoId es obligatorio")
    private Long medicoId;

    @NotNull(message="El campo pacienteId es obligatorio")
    private Long pacienteId;

    @NotNull(message="El campo estudioId es obligatorio")
    private Long estudioId;

    
   private Boolean autorizado;
   

   private LocalDate fechaVigencia;
   
   
   private String codigoOrden;
   
   private EstadoOrden estadoOrden;
   @NotNull(message="No puede ser nulo")
   private Integer prioridad;
   
   
   

   public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

   

   public EstadoOrden getEstadoOrden() {
		return estadoOrden;
	}

	public void setEstadoOrden(EstadoOrden estadoOrden) {
		this.estadoOrden = estadoOrden;
	}
   

   public String getCodigoOrden() {
		return codigoOrden;
	}

	public void setCodigoOrden(String codigoOrden) {
		this.codigoOrden = codigoOrden;
	}

   

   public LocalDate getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(LocalDate fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}
    

    public Boolean getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Boolean autorizado) {
		this.autorizado = autorizado;
	}
    public OrdenEstudioRequestDto() {}

    public OrdenEstudioRequestDto(LocalDate fecha, Long medicoId, Long pacienteId, Long estudioId,EstadoOrden estadoOrden) {
        this.fecha = fecha;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
        this.estudioId = estudioId;
        this.estadoOrden=estadoOrden;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getEstudioId() {
        return estudioId;
    }

    public void setEstudioId(Long estudioId) {
        this.estudioId = estudioId;
    }
}
