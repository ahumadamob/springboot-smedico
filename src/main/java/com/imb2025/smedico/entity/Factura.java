package com.imb2025.smedico.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private Double monto;

    
    @ManyToOne
    @JoinColumn(name = "medio_pago_id")
    private MedioPago medioPago; // Cambiado: Usamos la entidad en lugar del Id

    public MedioPago getMedioPago() { //Agregado
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) { //Agregado
		this.medioPago = medioPago;
	}

	public Factura() {
    }

    public Factura(LocalDate fecha, Double monto, MedioPago medioPago) {
        this.fecha = fecha;
        this.monto = monto;
        this.medioPago = medioPago; //Modificado: dato tipo Entidad MedioPago
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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    
}
