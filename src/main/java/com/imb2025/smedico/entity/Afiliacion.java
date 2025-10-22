package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "afiliaciones")
public class Afiliacion extends BaseEntity {

    @Column(nullable = false)
    private Long numeroAfiliado;

    @Column(nullable = false)
    private LocalDate fechaVigenciaDesde;

    @Column(nullable = false)
    private LocalDate fechaHasta;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "obra_id", nullable = false)
    private ObraSocial obra;

    // === Ejercicio 1: atributo booleano ===
    @Column(nullable = false)
    private boolean activa = true;

    public Afiliacion() {}

    public Afiliacion(Long numeroAfiliado,
                      LocalDate fechaVigenciaDesde,
                      LocalDate fechaHasta,
                      Paciente paciente,
                      ObraSocial obra) {
        this.numeroAfiliado = numeroAfiliado;
        this.fechaVigenciaDesde = fechaVigenciaDesde;
        this.fechaHasta = fechaHasta;
        this.paciente = paciente;
        this.obra = obra;
    }

    public Long getNumeroAfiliado() { return numeroAfiliado; }
    public void setNumeroAfiliado(Long numeroAfiliado) { this.numeroAfiliado = numeroAfiliado; }

    public LocalDate getFechaVigenciaDesde() { return fechaVigenciaDesde; }
    public void setFechaVigenciaDesde(LocalDate fechaVigenciaDesde) { this.fechaVigenciaDesde = fechaVigenciaDesde; }

    public LocalDate getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(LocalDate fechaHasta) { this.fechaHasta = fechaHasta; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public ObraSocial getObra() { return obra; }
    public void setObra(ObraSocial obra) { this.obra = obra; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}
