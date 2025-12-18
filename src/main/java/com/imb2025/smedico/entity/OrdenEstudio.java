package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import utilities.EstadoOrden;

import java.time.LocalDate;

@Entity
public class OrdenEstudio extends BaseEntity {


    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "medicoId")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "pacienteId")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "estudioId")
    private Estudio estudio;
    
    private Boolean autorizado;
    
    private String codigoOrden;

    private LocalDate fechaVigencia;

  
    private EstadoOrden estadoOrden;
    
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

	public OrdenEstudio() {}

    public OrdenEstudio(LocalDate fecha, Medico medico, Paciente paciente, Estudio estudio) {
    	super();
        this.fecha = fecha;
        this.medico = medico;
        this.paciente = paciente;
        this.estudio = estudio;
    }

 

    public LocalDate getFecha() {
        return fecha;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Estudio getEstudio() {
        return estudio;
    }



    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }
}
