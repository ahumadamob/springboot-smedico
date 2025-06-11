package com.imb2025.smedico.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class OrdenEstudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "medicoId") 
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "pacienteId")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "estudioId")
    private EstudioEntity estudio;
    
    // Constructor 
    public OrdenEstudio() {}
    
    
    public OrdenEstudio(LocalDate fecha, Medico medico,Paciente paciente, EstudioEntity estudio) {
    	this.fecha=fecha;
    	this.paciente=paciente;
    	this.medico=medico;
    	this.estudio=estudio;
    }
    
        
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public EstudioEntity getEstudio() {
		return estudio;
	}

	public void setEstudio(EstudioEntity estudio) {
		this.estudio = estudio;
	}

	public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
