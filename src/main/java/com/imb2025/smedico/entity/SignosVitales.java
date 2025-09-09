package com.imb2025.smedico.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SignosVitales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate fecha_hora;
	private Double peso;
	private Double altura;
	private Double imc; 
	private Double temperatura;
	private Integer frecuencia_cardiaca;
	private Integer presion_sistolica;
	private Integer presion_diastolica;
	private Integer saturacion_o2;
	private String observaciones;
	
	@ManyToOne
    private Consulta consulta;

	public SignosVitales() {}

	public SignosVitales(Long id, LocalDate fecha_hora, Double peso, Double altura, Double imc, Double temperatura,
			Integer recuencia_cardiaca, Integer presion_sistolica, Integer presion_diastolica, Integer saturacion_o2,
			String observaciones, Consulta consulta) {
		super();
		this.id = id;
		this.fecha_hora = fecha_hora;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
		this.temperatura = temperatura;
		this.frecuencia_cardiaca = recuencia_cardiaca;
		this.presion_sistolica = presion_sistolica;
		this.presion_diastolica = presion_diastolica;
		this.saturacion_o2 = saturacion_o2;
		this.observaciones = observaciones;
		this.consulta = consulta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(LocalDate fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Integer getFrecuencia_cardiaca() {
		return frecuencia_cardiaca;
	}

	public void setFrecuencia_cardiaca(Integer recuencia_cardiaca) {
		this.frecuencia_cardiaca = recuencia_cardiaca;
	}

	public Integer getPresion_sistolica() {
		return presion_sistolica;
	}

	public void setPresion_sistolica(Integer presion_sistolica) {
		this.presion_sistolica = presion_sistolica;
	}

	public Integer getPresion_diastolica() {
		return presion_diastolica;
	}

	public void setPresion_diastolica(Integer presion_diastolica) {
		this.presion_diastolica = presion_diastolica;
	}

	public Integer getSaturacion_o2() {
		return saturacion_o2;
	}

	public void setSaturacion_o2(Integer saturacion_o2) {
		this.saturacion_o2 = saturacion_o2;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
}