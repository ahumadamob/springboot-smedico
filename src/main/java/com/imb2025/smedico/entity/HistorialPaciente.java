package com.imb2025.smedico.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class HistorialPaciente extends HistorialPacienteBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pacienteId", nullable = false)
    @JsonIgnore
    private Paciente paciente;
    
    @Column(nullable = false, length = 255)
    private String evento;
    
    @Column(nullable = false)
    private LocalDate fecha;
    
    @Column(length = 255)
    private String observacion;

    public HistorialPaciente() {}

    public HistorialPaciente(String evento, LocalDate fecha, String observacion, Paciente paciente) {
        this.evento = evento;
        this.fecha = fecha;
        this.observacion = observacion;
        this.paciente = paciente;
    }
    
    @JsonProperty("pacienteId")
    public Long getPacienteId() {
        return paciente != null ? paciente.getId() : null;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getEvento() {
        return evento;
    }
    public void setEvento(String evento) {
        this.evento = evento;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
