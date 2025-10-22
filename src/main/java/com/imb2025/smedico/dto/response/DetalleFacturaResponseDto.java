package com.imb2025.smedico.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalleFacturaResponseDto {
    private Long id;
    private String descripcion;
    private BigDecimal importe;
    private Long facturaId;
    private Integer version;
    private LocalDate fechaVigencia; // 🔹 nuevo campo

    public DetalleFacturaResponseDto() {}

    public DetalleFacturaResponseDto(Long id, String descripcion, BigDecimal importe, Long facturaId, Integer version, LocalDate fechaVigencia) {
        this.id = id;
        this.descripcion = descripcion;
        this.importe = importe;
        this.facturaId = facturaId;
        this.version = version;
        this.fechaVigencia = fechaVigencia;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }

    public Long getFacturaId() { return facturaId; }
    public void setFacturaId(Long facturaId) { this.facturaId = facturaId; }

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }

    public LocalDate getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(LocalDate fechaVigencia) { this.fechaVigencia = fechaVigencia; }
}
