package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @ManyToOne
    private Paciente paciente;

    private Double monto;
    public MedioPago getMedioPago() { //Agregado
		return medioPago;
	}

    private Long medioPagoId;
	public void setMedioPago(MedioPago medioPago) { //Agregado
		this.medioPago = medioPago;
	}

    public Factura() {
    }

    public Factura(LocalDate fecha, Paciente paciente, Double monto, Long medioPagoId) {
        this.fecha = fecha;
        this.paciente = paciente;
        this.monto = monto;
        this.medioPagoId = medioPagoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMedioPagoId() {
        return medioPagoId;
    }

    public void setMedioPagoId(Long medioPagoId) {
        this.medioPagoId = medioPagoId;
    }
}
