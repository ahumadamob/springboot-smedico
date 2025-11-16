package com.imb2025.smedico.dto.response;

import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.Paciente;

import java.time.LocalDate;

public class FacturaResponseDto {

    private Long id;

    private Long version;

    private LocalDate fecha;

    private Paciente paciente;

    private Double monto;

    private MedioPago medioPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }
}
