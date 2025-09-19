package com.imb2025.smedico.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SignosVitalesRequestDto {

	@NotNull(message = "La fecha de los signos vitales es obligatoria")
	private LocalDate fecha_hora;

	@NotNull(message = "El peso registrado en los Signos Vitales es obligatorio")
	@Min(value = 1, message = "El peso registrado en los Signos Vitales debe ser positivo")
	private Double peso;

	@NotNull(message = "La altura registrada en los Signos Vitales es obligatoria")
	@Min(value = 1, message = "La altura registrada en los Signos Vitales debe ser positiva")
	private Double altura;

	@NotNull(message = "El indice de masa corporal registrado en los Signos Vitales es obligatorio")
	@Min(value = 1, message = "El indice de masa corporal registrado en los Signos Vitales debe ser positivo")
	private Double imc;

	@NotNull(message = "La temperatura registrada en los Signos Vitales es obligatoria")
	@Min(value = 1, message = "La temperatura registrada en los Signos Vitales debe ser positiva")
	private Double temperatura;

	@NotNull(message = "La frecuencia cardiaca registrada en los Signos Vitales es obligatoria")
	@Min(value = 1, message = "La frecuencia cardiaca registrada en los Signos Vitales debe ser positiva")
	private Integer frecuencia_cardiaca;

	@NotNull(message = "La presion sistolica registrada en los Signos Vitales es obligatoria")
	@Min(value = 1, message = "La presion sistolica registrada en los Signos Vitales debe ser positiva")
	private Integer presion_sistolica;

	@NotNull(message = "La presion diastolica registrada en los Signos Vitales es obligatoria")
	@Min(value = 1, message = "La presion diastolica registrada en los Signos Vitales debe ser positiva")
	private Integer presion_diastolica;

	@NotNull(message = "La saturacion de o2 registrada en los Signos Vitales es obligatoria")
	@Min(value = 1, message = "La saturacion de o2 registrada en los Signos Vitales debe ser positiva")
	private Integer saturacion_o2;

	private String observaciones;

	@NotNull(message = "El ID de la consulta social es obligatoria")
	@Min(value = 1, message = "El ID de la consulta debe ser mayor a 0")
	private Long idConsulta;

	public SignosVitalesRequestDto() {}

	public SignosVitalesRequestDto(
			LocalDate fecha_hora,
			Double peso,
			Double altura,
			Double imc,
			Double temperatura,
			Integer frecuencia_cardiaca,
			Integer presion_sistolica,
			Integer presion_diastolica,
			Integer saturacion_o2,
			String observaciones,
			Long idConsulta) {
		this.fecha_hora = fecha_hora;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
		this.temperatura = temperatura;
		this.frecuencia_cardiaca = frecuencia_cardiaca;
		this.presion_sistolica = presion_sistolica;
		this.presion_diastolica = presion_diastolica;
		this.saturacion_o2 = saturacion_o2;
		this.observaciones = observaciones;
		this.idConsulta = idConsulta;
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

	public void setFrecuencia_cardiaca(Integer frecuencia_cardiaca) {
		this.frecuencia_cardiaca = frecuencia_cardiaca;
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

	public Long getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}
	
}
