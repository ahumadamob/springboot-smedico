package com.imb2025.smedico.dto;

public class DetalleFacturaRequestDto {
    private String descripcion;
    private Double importe;
    private Long facturaId;

    public DetalleFacturaRequestDto() {}

    public DetalleFacturaRequestDto(String descripcion, Double importe, Long facturaId) {
        this.descripcion = descripcion;
        this.importe = importe;
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

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }
}
