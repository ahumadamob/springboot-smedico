package com.imb2025.smedico.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MedioPago extends BaseEntity {

	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;*/
	
	@Column(nullable = false, length = 30) 
	private String nombre;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false) 
	private TipoPago tipo;
	
	//a
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Estado estado;

	
	@JsonIgnore 
	@OneToMany(mappedBy = "medioPago")
    private List<Factura> facturas; 
	
	
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

	//a
	public Estado getEstado() {
        return estado;
    }
	//a
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
	
	
	public enum TipoPago {
		TARJETA_CREDITO_DEBITO,
		MERCADO_PAGO,
		CANJE_CUPON
	}
	
	public enum Estado { //a
	    ACTIVO,
	    INACTIVO
	}

	
}
