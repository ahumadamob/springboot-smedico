package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    
    private String codigoOrden;
    
    private LocalDate fechaVigencia;
    
    
  

	public OrdenEstudio() {}

    public OrdenEstudio(LocalDate fecha, Medico medico, Paciente paciente, Estudio estudio) {
        this.fecha = fecha;
        this.medico = medico;
        this.paciente = paciente;
        this.estudio = estudio;
    }

 
    public LocalDate getFechaVigencia() {
  		return fechaVigencia;
  	}

  	public void setFechaVigencia(LocalDate fechaVigencia) {
  		this.fechaVigencia = fechaVigencia;
  	}
    
    public String getCodigoOrden() {
		return codigoOrden;
	}

	public void setCodigoOrden(String codigoOrden) {
		this.codigoOrden = codigoOrden;
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
