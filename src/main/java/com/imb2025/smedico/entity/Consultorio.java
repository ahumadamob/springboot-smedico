package com.imb2025.smedico.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Version;

@Entity
public class Consultorio extends BaseEntity{
    
    private String nombre;
    private String ubicacion;
    private int piso;
    private String identificadorLegible;
    private LocalDate fechaArchivado;
    @Version
    private Long version;

    public Consultorio() {}

    public Consultorio(String nombre, String ubicacion, int piso, String identificadorLegible, LocalDate fechaArchivado) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.piso = piso;
        this.identificadorLegible = identificadorLegible;
        this.fechaArchivado = fechaArchivado;
    }

    public String getIdentificadorLegible() {
		return identificadorLegible;
	}

	public void setIdentificadorLegible(String identificadorLegible) {
		this.identificadorLegible = identificadorLegible;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public int getPiso() {
        return piso;
    }
    public void setPiso(int piso) {
        this.piso = piso;
    }
    public LocalDate getFechaArchivado() {
		return fechaArchivado;
	}

	public void setFechaArchivado(LocalDate fechaArchivado) {
		this.fechaArchivado = fechaArchivado;
	}
}
