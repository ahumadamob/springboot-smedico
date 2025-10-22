package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "detalle_factura")
public class DetalleFactura extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal importe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;

    @Column(nullable = false)
    private LocalDate fechaVigencia; // 🔹 nuevo campo requerido

    public DetalleFactura() {}

    public DetalleFactura(String descripcion, BigDecimal importe, Factura factura, LocalDate fechaVigencia) {
        this.descripcion = descripcion;
        this.importe = importe;
        this.factura = factura;
        this.fechaVigencia = fechaVigencia;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }

    public Factura getFactura() { return factura; }
    public void setFactura(Factura factura) { this.factura = factura; }

    public LocalDate getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(LocalDate fechaVigencia) { this.fechaVigencia = fechaVigencia; }

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

