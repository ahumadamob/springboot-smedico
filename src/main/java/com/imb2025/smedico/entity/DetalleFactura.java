package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_factura") // Nombre explícito de la tabla
public class DetalleFactura extends BaseEntity {

    @Column(nullable = false, length = 255) // Restricciones explícitas
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2) // BigDecimal para dinero
    private BigDecimal importe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) // Lazy loading y obligatorio
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;

    @Version
    @Column(nullable = false)
    private Integer version;

    // 🔹 Constructores
    public DetalleFactura() {}

    public DetalleFactura(Long id, String descripcion, BigDecimal importe, Factura factura) {
        super(id);
        this.descripcion = descripcion;
        this.importe = importe;
        this.factura = factura;
    }

    // 🔹 Getters y Setters
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

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }

    // 🔹 equals y hashCode basados en id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleFactura)) return false;
        DetalleFactura that = (DetalleFactura) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
