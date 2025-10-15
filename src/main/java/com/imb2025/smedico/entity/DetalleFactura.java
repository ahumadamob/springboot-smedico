package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_factura") // Nombre explícito de la tabla
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255) // Restricciones explícitas
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2) // BigDecimal para dinero
    private BigDecimal importe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Lazy loading y obligatorio
    @JoinColumn(name = "factura_id", nullable = false)
    @JsonIgnore 
    private Factura factura;

    // 🔹 Constructores
    public DetalleFactura() {}

    public DetalleFactura(Long id, String descripcion, BigDecimal importe, Factura factura) {
        this.id = id;
        this.descripcion = descripcion;
        this.importe = importe;
        this.factura = factura;
    }

    // 🔹 Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getImporte() {
        return importe;
    }
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Factura getFactura() {
        return factura;
    }
    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    // 🔹 equals y hashCode basados en id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleFactura)) return false;
        DetalleFactura that = (DetalleFactura) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
