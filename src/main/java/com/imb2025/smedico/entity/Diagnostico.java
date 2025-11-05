package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "diagnostico")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Diagnostico extends BaseEntity{

    @Column(name = "consulta_id")
    private Long consultaId;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_diagnostico")
    private LocalDate fechaDiagnostico;
    
    @Column(name = "identificador_legible")
    private String identificadorLegible;

    @Column(name = "fecha_vigencia")
    private LocalDate fechaVigencia;
    
    public Diagnostico() { }

    public Diagnostico(Long consultaId, String descripcion, LocalDate fechaDiagnostico, String identificadorLegible, LocalDate fechaVigencia) {
        this.consultaId = consultaId;
        this.descripcion = descripcion;
        this.fechaDiagnostico = fechaDiagnostico;
        this.identificadorLegible = identificadorLegible;
        this.fechaVigencia = fechaVigencia;
    }


    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaDiagnostico() { return fechaDiagnostico; }
    public void setFechaDiagnostico(LocalDate fechaDiagnostico) { this.fechaDiagnostico = fechaDiagnostico; }
    
    public String getIdentificadorLegible() { return identificadorLegible; }
    public void setIdentificadorLegible(String identificadorLegible) { this.identificadorLegible = identificadorLegible; }

    public LocalDate getFechaVigencia() { return fechaVigencia; }
    
    public void setFechaVigencia(LocalDate fechaVigencia) { this.fechaVigencia = fechaVigencia; }

}
