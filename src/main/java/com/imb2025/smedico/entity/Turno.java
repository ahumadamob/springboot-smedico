package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;

    @ManyToOne
	private Paciente paciente;
    @ManyToOne
	private Medico medico;
    @ManyToOne
	private EstadoTurno estadoTurno;

    public Turno() {}
    
    public Turno(LocalDate fecha, LocalTime hora, Paciente paciente, Medico medico, EstadoTurno estadoTurno) {
    	  super();
	    	this.fecha = fecha;
	        this.hora = hora;
	        this.paciente = paciente;
	        this.medico = medico;
	        this.estadoTurno = estadoTurno;
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

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Paciente getPaciente(){
    	return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
    this.paciente = paciente;	
    }
    
    public Medico getMedico(){
    	return medico;
    }
    
    public void setMedico(Medico medico) {
    this.medico = medico;	
    }
    
    public EstadoTurno getEstadoTurno(){
    	return  estadoTurno;
    }
    
    public void setEstadoTurno(EstadoTurno estadoTurno) {
    this.estadoTurno = estadoTurno;	
    }
    
}