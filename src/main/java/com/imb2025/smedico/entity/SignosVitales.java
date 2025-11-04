package com.imb2025.smedico.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class SignosVitales extends BaseEntity{


    // fecha y hora completa
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
    private LocalDate fechaVigencia;
    private String codigoRegistro;



    @ManyToOne
    private Consulta consulta;

    public SignosVitales() {}

    public SignosVitales(LocalDateTime fechaHora, Double peso, Double altura, Double imc, Double temperatura,
			Integer frecuenciaCardiaca, Integer presionSistolica, Integer presionDiastolica, Integer saturacionO2,
			String observaciones, LocalDate fechaVigencia, Consulta consulta, String codigoRegistro) {
		super();
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
		this.fechaVigencia = fechaVigencia;
		this.consulta = consulta;
		this.codigoRegistro = codigoRegistro;
	}

	public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public Double getImc() { return imc; }
    public void setImc(Double imc) { this.imc = imc; }

    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }

    public Integer getFrecuenciaCardiaca() { return frecuenciaCardiaca; }
    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) { this.frecuenciaCardiaca = frecuenciaCardiaca; }

    public Integer getPresionSistolica() { return presionSistolica; }
    public void setPresionSistolica(Integer presionSistolica) { this.presionSistolica = presionSistolica; }

    public Integer getPresionDiastolica() { return presionDiastolica; }
    public void setPresionDiastolica(Integer presionDiastolica) { this.presionDiastolica = presionDiastolica; }

    public Integer getSaturacionO2() { return saturacionO2; }
    public void setSaturacionO2(Integer saturacionO2) { this.saturacionO2 = saturacionO2; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }

	public LocalDate getFechaVigencia() {
		return fechaVigencia;
	}
	public void setFechaVigencia(LocalDate fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	public String getCodigoRegistro() {
		return codigoRegistro;
	}
	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}
    
	
}
