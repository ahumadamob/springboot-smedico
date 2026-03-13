package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "diagnostico")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Diagnostico extends BaseEntity {

    @Column(name = "consulta_id")
    private Long consultaId;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_diagnostico")
    private LocalDate fechaDiagnostico;
    
    @Column(name = "codigo_referencia", unique = true, nullable = false)
    private String codigoReferencia;

    public Diagnostico() { }

    public Diagnostico(Long consultaId, String descripcion, LocalDate fechaDiagnostico, String codigoReferencia) {
        this.consultaId = consultaId;
        this.descripcion = descripcion;
        this.fechaDiagnostico = fechaDiagnostico;
        this.codigoReferencia = codigoReferencia;
    }

    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaDiagnostico() { return fechaDiagnostico; }
    public void setFechaDiagnostico(LocalDate fechaDiagnostico) { this.fechaDiagnostico = fechaDiagnostico; }

	public String getCodigoReferencia() {
		return codigoReferencia;
	}

	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
    
    
}
