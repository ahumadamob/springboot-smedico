package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private LocalDate fechaDiagnostico;

    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    public Diagnostico() {}

    public Diagnostico(Long id, String descripcion, LocalDate fechaDiagnostico, Consulta consulta) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaDiagnostico = fechaDiagnostico;
        this.consulta = consulta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(LocalDate fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }
}
