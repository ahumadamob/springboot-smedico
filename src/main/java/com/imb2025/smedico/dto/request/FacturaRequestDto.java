package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class FacturaRequestDto {

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha;

    @NotNull(message = "El id del paciente es obligatorio")
    @Positive(message = "El id debe ser un número positivo")
    private Long pacienteId;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El monto debe tener máximo 2 decimales")
    private Double monto;

    @NotNull(message = "El id del medio de pago es obligatorio")
    @Positive(message = "El id del medio de pago debe ser un número positivo")
    private Long medioPagoId;

    @NotNull(message = "El estado del pago es obligatorio")
    private Boolean isPagada;

    public FacturaRequestDto() {}

    public FacturaRequestDto(LocalDate fecha, Long pacienteId, Double monto, Long medioPagoId, Boolean isPagada) {
        this.fecha = fecha;
        this.pacienteId = pacienteId;
        this.monto = monto;
        this.medioPagoId = medioPagoId;
        this.isPagada = isPagada;
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

    public Boolean getPagada() {
        return isPagada;
    }

    public void setPagada(Boolean pagada) {
        isPagada = pagada;
    }
}
