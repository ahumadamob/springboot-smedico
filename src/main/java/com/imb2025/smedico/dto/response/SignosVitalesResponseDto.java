package com.imb2025.smedico.dto.response;

import java.time.LocalDateTime;

public class SignosVitalesResponseDto {

	private Long id;
	private Long version;
	private LocalDateTime fechaHora;
	private Double peso;
	private Double altura;
	private Double imc;
	private Double temperatura;
	private Integer frecuenciaCardiaca;
	private Integer presionSistolica;
	private Integer presionDiastolica;
	private Integer saturacionO2;
	private String observaciones;
	private Long idConsulta;
	private boolean publicado;

	public SignosVitalesResponseDto(Long id, LocalDateTime fechaHora, Double peso, Double altura, Double imc,
			Double temperatura, Integer frecuenciaCardiaca, Integer presionSistolica, Integer presionDiastolica,
			Integer saturacionO2, String observaciones, Long idConsulta, Long version, boolean publicado) {
		this.id = id;
		this.fechaHora = fechaHora;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
		this.temperatura = temperatura;
		this.frecuenciaCardiaca = frecuenciaCardiaca;
		this.presionSistolica = presionSistolica;
		this.presionDiastolica = presionDiastolica;
		this.saturacionO2 = saturacionO2;
		this.observaciones = observaciones;
		this.idConsulta = idConsulta;
		this.version = version;
		this.publicado = publicado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
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

	public Integer getFrecuenciaCardiaca() {
		return frecuenciaCardiaca;
	}

	public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) {
		this.frecuenciaCardiaca = frecuenciaCardiaca;
	}

	public Integer getPresionSistolica() {
		return presionSistolica;
	}

	public void setPresionSistolica(Integer presionSistolica) {
		this.presionSistolica = presionSistolica;
	}

	public Integer getPresionDiastolica() {
		return presionDiastolica;
	}

	public void setPresionDiastolica(Integer presionDiastolica) {
		this.presionDiastolica = presionDiastolica;
	}

	public Integer getSaturacionO2() {
		return saturacionO2;
	}

	public void setSaturacionO2(Integer saturacionO2) {
		this.saturacionO2 = saturacionO2;
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

	public boolean isPublicado() {
		return publicado;
	}

	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}
	
}
