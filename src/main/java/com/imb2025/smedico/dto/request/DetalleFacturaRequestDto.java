package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class DetalleFacturaRequestDto {
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 3, max = 100, message = "La descripción debe tener entre 3 y 100 caracteres")
    private String descripcion;

    @NotNull(message = "El importe es obligatorio")
    @Positive(message = "El importe debe ser positivo")
    private Double importe;

    @NotNull(message = "El ID de factura es obligatorio")
    @Positive(message = "El ID de factura debe ser positivo")
    private Long facturaId;

    @NotNull(message = "La fecha de vigencia es obligatoria")
    private LocalDate fechaVigencia; // 🔹 nuevo campo

    public DetalleFacturaRequestDto() {}

    public DetalleFacturaRequestDto(String descripcion, Double importe, Long facturaId, LocalDate fechaVigencia) {
        this.descripcion = descripcion;
        this.importe = importe;
        this.facturaId = facturaId;
        this.fechaVigencia = fechaVigencia;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getImporte() { return importe; }
    public void setImporte(Double importe) { this.importe = importe; }

    public Long getFacturaId() { return facturaId; }
    public void setFacturaId(Long facturaId) { this.facturaId = facturaId; }

    public LocalDate getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(LocalDate fechaVigencia) { this.fechaVigencia = fechaVigencia; }
}
