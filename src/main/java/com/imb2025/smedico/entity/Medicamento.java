package com.imb2025.smedico.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Medicamento extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 50)
    private String presentacion;
    
    @Column(nullable = true, length = 100)
    private String dosisSugerida;

    public Medicamento() {}

    public Medicamento(String nombre, String presentacion, String dosisSugerida) {

        this.nombre = nombre;
        this.presentacion = presentacion;
        this.dosisSugerida = dosisSugerida;
    }


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPresentacion() {
        return presentacion;
    }
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    public String getDosisSugerida() {
        return dosisSugerida;
    }
    public void setDosisSugerida(String dosisSugerida) {
        this.dosisSugerida = dosisSugerida;
    }
}
