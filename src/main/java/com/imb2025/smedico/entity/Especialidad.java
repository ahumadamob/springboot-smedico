package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Especialidad extends BaseEntity{
    
    private String nombre;
    private String descripcion;
    private Boolean atributoBooleano = false;
    private String alias;
    
	

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Especialidad() {}

    public Especialidad(String nombre, String descripcion, boolean atributoBooleano, String alias ) {
        
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.atributoBooleano = atributoBooleano;
        this.alias = alias;
        
        //this.categoria = categoria;
       
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
    public Boolean getAtributoBooleano() {
		return atributoBooleano;
	}
public void setAtributoBooleano(Boolean atributoBooleano) {
		this.atributoBooleano = atributoBooleano;
	}
}
