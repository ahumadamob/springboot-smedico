package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalTime;

@Entity
public class ResultadoEstudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ordenEstudioID")
    private OrdenEstudio ordenEstudio;

    @OneToOne(mappedBy = "resultadoEstudio")
    private Estudio estudio;
    private Long resultado;
    private LocalTime fechaCarga;
    private String observaciones;

    public ResultadoEstudio() {}

    public ResultadoEstudio(long id, OrdenEstudio ordenEstudio, Estudio estudio, Long resultado, LocalTime fechaCarga,
                             String observaciones) {
        this.id = id;
        this.ordenEstudio = ordenEstudio;
        this.estudio = estudio;
        this.resultado = resultado;
        this.fechaCarga = fechaCarga;
        this.observaciones = observaciones;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

	public LocalTime getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(LocalTime fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
