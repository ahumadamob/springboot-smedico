package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ubicacion;
    private int piso;
    private String identificadorLegible;
    private Long version;

    public Consultorio() {}

    public Consultorio(Long id, String nombre, String ubicacion, int piso, String identificadorLegible, Long version) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.piso = piso;
        this.identificadorLegible = identificadorLegible;
        this.version = version;
        
    }

    public Long getId() {
        return id;
    }
    
	public void setId(Long id) {
        this.id = id;
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
    
    public void setIdentificadorLegible(String identificadorLegible) {
		this.identificadorLegible = identificadorLegible;
	}

	public String getIdentificadorLegible() {
		return identificadorLegible;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
