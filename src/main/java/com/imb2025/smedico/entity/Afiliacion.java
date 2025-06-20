package com.imb2025.smedico.entity;

import jakarta.persistence.Column;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Afiliacion {

	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relaciones similares a Estudio.java
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "obra_social_id")
    private ObraSocial obraSocial;

    @Column(name = "numero_afiliado")
    private Long numeroAfiliado;

    @Column(name = "fecha_vigencia_desde")
    private LocalDate fechaVigenciaDesde;

    @Column(name = "fecha_hasta")
    private LocalDate fechaHasta;

    // Constructores
    public Afiliacion() {}

    public Afiliacion(Paciente paciente, ObraSocial obraSocial, Long numeroAfiliado, LocalDate fechaVigenciaDesde, LocalDate fechaHasta) {
        this.paciente = paciente;
        this.obraSocial = obraSocial;
        this.numeroAfiliado = numeroAfiliado;
        this.fechaVigenciaDesde = fechaVigenciaDesde;
        this.fechaHasta = fechaHasta;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Long getNumeroAfiliado() {
        return numeroAfiliado;
    }

    public void setNumeroAfiliado(Long numeroAfiliado) {
        this.numeroAfiliado = numeroAfiliado;
    }

    public LocalDate getFechaVigenciaDesde() {
        return fechaVigenciaDesde;
    }

    public void setFechaVigenciaDesde(LocalDate fechaVigenciaDesde) {
        this.fechaVigenciaDesde = fechaVigenciaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
}