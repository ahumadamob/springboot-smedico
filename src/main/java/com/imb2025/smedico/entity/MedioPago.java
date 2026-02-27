package com.imb2025.smedico.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
public class MedioPago extends BaseEntity { //a extends
	
	@Column(nullable = false, length = 30) 
	private String nombre;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false) 
	private TipoPago tipo;

	@JsonIgnore 
	@OneToMany(mappedBy = "medioPago")
    private List<Factura> facturas; 
	
	//@Size (min = 1, max= 10 )
	private int severidad;
	
	
	public int getSeveridad() {
		return severidad;
	}


	public void setSeveridad(int severidad) {
		this.severidad = severidad;
	}


	public List<Factura> getFacturas() {
		return facturas;
	}


	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
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
