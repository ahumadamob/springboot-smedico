package com.imb2025.smedico.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MedioPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	@Enumerated(EnumType.STRING)
    //@Column(nullable = false)
	private TipoPago tipo;
	
	@OneToMany(mappedBy = "medioPago")
    private List<Factura> facturas; // nos relacionamos con Factura
	
	
	public List<Factura> getFacturas() {
		return facturas;
	}


	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public TipoPago getTipo() {
		return tipo;
	}


	public void setTipo(TipoPago tipo) {
		this.tipo = tipo;
	}


	public enum TipoPago {
		TARJETA_CREDITO_DEBITO,
		MERCADO_PAGO,
		CANJE_CUPON
	}
	
}
