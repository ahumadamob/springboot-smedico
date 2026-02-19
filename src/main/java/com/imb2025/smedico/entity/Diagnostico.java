package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "diagnostico")
public class Diagnostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consulta_id")
    private Long consultaId;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_diagnostico")
    private LocalDate fechaDiagnostico;

    public Diagnostico() { }

    public Diagnostico(Long consultaId, String descripcion, LocalDate fechaDiagnostico) {
        this.consultaId = consultaId;
        this.descripcion = descripcion;
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaDiagnostico() { return fechaDiagnostico; }
    public void setFechaDiagnostico(LocalDate fechaDiagnostico) { this.fechaDiagnostico = fechaDiagnostico; }
}
