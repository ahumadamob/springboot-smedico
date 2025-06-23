package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medicamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String presentacion;
	private String dosisSugerida;
	

	//Constructor vacio
	public Medicamento() {}
	
	//Contructor con los campos
	public Medicamento(String nombre, String presentacion, String dosisSugerida) {
		super();
		this.nombre = nombre;
		this.presentacion = presentacion;
		this.dosisSugerida = dosisSugerida;
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
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public String getDosisSugerida() {
		return dosisSugerida;
	}
	public void setDosisSugerida(String dosisSugerida) {
		this.dosisSugerida = dosisSugerida;
	}
	
	
}