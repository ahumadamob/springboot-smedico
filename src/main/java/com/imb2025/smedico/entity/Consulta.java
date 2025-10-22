package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "consulta")
public class Consulta extends BaseEntity {

    @Column(nullable = false)
    private LocalDate fecha;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turno_id", nullable = false, unique = true)
    private Turno turno;

    @Column(name = "duracion_min", nullable = false)
    private Integer duracionMin;

    @Column(length = 500)
    private String comentarios;

    @Column(name = "fecha_vigencia")
    private LocalDate fechaVigencia;

    // ✅ NUEVO: Identificador legible (NO único en schema)
    @Column(name = "identificador_legible", nullable = false)
    private String identificadorLegible;

    // Constructores
    public Consulta() {}

    // Getters y Setters
    public LocalDate getFecha() { 
        return fecha; 
    }
    
    public void setFecha(LocalDate fecha) { 
        this.fecha = fecha; 
    }

    public Turno getTurno() { 
        return turno; 
    }
    
    public void setTurno(Turno turno) { 
        this.turno = turno; 
    }

    public Integer getDuracionMin() { 
        return duracionMin; 
    }
    
    public void setDuracionMin(Integer duracionMin) { 
        this.duracionMin = duracionMin; 
    }

    public String getComentarios() { 
        return comentarios; 
    }
    
    public void setComentarios(String comentarios) { 
        this.comentarios = comentarios; 
    }

    public LocalDate getFechaVigencia() { 
        return fechaVigencia; 
    }
    
    public void setFechaVigencia(LocalDate fechaVigencia) { 
        this.fechaVigencia = fechaVigencia; 
    }

    // ✅ NUEVO: Getter y Setter para identificadorLegible
    public String getIdentificadorLegible() {
        return identificadorLegible;
    }

    public void setIdentificadorLegible(String identificadorLegible) {
        this.identificadorLegible = identificadorLegible;
    }
}