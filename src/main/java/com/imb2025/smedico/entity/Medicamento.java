package com.imb2025.smedico.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 50)
    private String presentacion;
    
    @Column(nullable = true, length = 100)
    private String dosisSugerida;

    public Medicamento() {}

    public Medicamento(Long id, String nombre, String presentacion, String dosisSugerida) {
        this.id = id;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.dosisSugerida = dosisSugerida;
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
