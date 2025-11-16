package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;

@Entity
public class Especialidad extends BaseEntity{
    
    private String nombre;
    private String descripcion;

    public Especialidad() {}

    public Especialidad(String nombre, String descripcion) {
        
        this.nombre = nombre;
        this.descripcion = descripcion;
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
}
