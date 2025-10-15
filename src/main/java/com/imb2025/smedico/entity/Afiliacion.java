package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "afiliaciones")
public class Afiliacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long numeroAfiliado;

    @Column(nullable = false)
    private LocalDate fechaVigenciaDesde;

    @Column(nullable = false)
    private LocalDate fechaHasta;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ObraSocial obra;

    @Version
    @Column(nullable = false)
    private Long version;

    public Afiliacion() {}

    public Afiliacion(Long id,
                      Long numeroAfiliado,
                      LocalDate fechaVigenciaDesde,
                      LocalDate fechaHasta,
                      Paciente paciente,
                      ObraSocial obra) {
        this.id = id;
        this.numeroAfiliado = numeroAfiliado;
        this.fechaVigenciaDesde = fechaVigenciaDesde;
        this.fechaHasta = fechaHasta;
        this.paciente = paciente;
        this.obra = obra;
    }

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; } // normalmente no se setea manualmente
}
