package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long facturaId;
    private String descripcion;
    private Double importe;

    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getFacturaId() {
        return facturaId;
    }
    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getImporte() {
        return importe;
    }
    public void setImporte(Double importe) {
        this.importe = importe;
    }
}
