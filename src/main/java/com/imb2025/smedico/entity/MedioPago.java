package com.imb2025.smedico.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MedioPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	//@Enumerated(EnumType.STRING)
    @Column(nullable = false)
	private TipoPago tipo;
	
	
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
