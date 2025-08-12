package com.imb2025.smedico.dto;

import java.time.LocalDate;

public class FacturaRequestDto {

    private LocalDate fecha;

    private Long pacienteId;

    private Double monto;

    private Long medioPagoId;

    public FacturaRequestDto() {}

    public FacturaRequestDto(LocalDate fecha, Long pacienteId, Double monto, Long medioPagoId) {
        this.fecha = fecha;
        this.pacienteId = pacienteId;
        this.monto = monto;
        this.medioPagoId = medioPagoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Long getMedioPagoId() {
        return medioPagoId;
    }

    public void setMedioPagoId(Long medioPagoId) {
        this.medioPagoId = medioPagoId;
    }
}
