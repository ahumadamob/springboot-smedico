package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Factura extends BaseEntity{

    private LocalDate fecha;

    @ManyToOne
    private Paciente paciente;

    private Double monto;

    @ManyToOne
    private MedioPago medioPago;

    public Factura() {}

    public Factura(LocalDate fecha, Paciente paciente, Double monto, MedioPago medioPago) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.monto = monto;
        this.medioPago = medioPago;
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
