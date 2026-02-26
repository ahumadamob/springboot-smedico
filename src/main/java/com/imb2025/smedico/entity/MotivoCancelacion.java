package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "motivo_cancelacion")
public class MotivoCancelacion extends BaseEntity{

    private String nombre;
    private String descripcion;
    private String alias;

    public MotivoCancelacion() {}

    public MotivoCancelacion(String nombre, String descripcion,String alias) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.alias=alias;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
