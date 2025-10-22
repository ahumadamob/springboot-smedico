package com.imb2025.smedico.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "motivo_cancelacion")
public class MotivoCancelacion extends BaseEntity{

    private String nombre;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;
    
    public enum Estado {
        ACTIVO,
        INACTIVO
    }

    public MotivoCancelacion() {}

    public MotivoCancelacion(String nombre, String descripcion) {
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
    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
