package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class ResultadoEstudio extends BaseEntity {
   

    @ManyToOne
    @JoinColumn(name = "ordenEstudioID")
    private OrdenEstudio ordenEstudio;

    @OneToOne(mappedBy = "resultadoEstudio")
    private Estudio estudio;
    private Long resultado;    
    private LocalDate fechaCarga;
    private String observaciones;

    public ResultadoEstudio() {}

    public ResultadoEstudio(OrdenEstudio ordenEstudio, Estudio estudio, Long resultado,
                             LocalDate fechaCarga, String observaciones) {
       
        this.ordenEstudio = ordenEstudio;
        this.estudio = estudio;
        this.resultado = resultado;       
        this.fechaCarga = fechaCarga;
        this.observaciones = observaciones;
    }


    public OrdenEstudio getOrdenEstudio() {
        return ordenEstudio;
    }

    public void setOrdenEstudio(OrdenEstudio ordenEstudio) {
        this.ordenEstudio = ordenEstudio;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }
    

    public Long getResultado() {
        return resultado;
    }

    public void setResultado(Long resultado) {
        this.resultado = resultado;
    }


    public LocalDate getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(LocalDate fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
