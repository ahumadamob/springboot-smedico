package com.imb2025.smedico.entity;

import jakarta.persistence.*;


//si atributos has de adherir, este camino debes seguir y que la fuerza te acompañe
//entidad-> request -> response -> mapper -> service -> controller -> repository

@Entity
@Table(name = "obrasocial")
public class ObraSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(length = 20)
    private String telefono;

    @Column(length = 150)
    private String direccion;

    @Column(length = 100)
    private String cobertura;

    @Column(nullable = false, unique = true)
    private String codigoReferencia;
    
    public ObraSocial() {}

    public ObraSocial(String nombre, String telefono, String direccion, String cobertura) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cobertura = cobertura;
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

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCobertura() {
        return cobertura;
    }
    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

	public String getCodigoReferencia() {
		return codigoReferencia;
	}

	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
    
}


