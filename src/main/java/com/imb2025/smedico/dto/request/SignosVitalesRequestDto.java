package com.imb2025.smedico.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

public class SignosVitalesRequestDto {

    @NotNull(message = "La fecha y hora de los signos vitales es obligatoria")
    @PastOrPresent(message = "La fecha y hora no puede ser futura")
    private LocalDateTime fechaHora;

    @NotNull(message = "El peso es obligatorio")
    @DecimalMin(value = "0.1", inclusive = true, message = "El peso debe ser mayor que 0")
    private Double peso;

    @NotNull(message = "La altura es obligatoria")
    @DecimalMin(value = "0.1", inclusive = true, message = "La altura debe ser mayor que 0")
    private Double altura;

    @NotNull(message = "El IMC es obligatorio")
    @DecimalMin(value = "0.1", inclusive = true, message = "El IMC debe ser mayor que 0")
    private Double imc;

    @NotNull(message = "La temperatura es obligatoria")
    // permitimos temperaturas válidas (p. ej. 30.0). Usamos un mínimo razonable.
    @DecimalMin(value = "20.0", inclusive = false, message = "La temperatura registrada parece inválida")
    private Double temperatura;

    @NotNull(message = "La frecuencia cardiaca es obligatoria")
    @Min(value = 1, message = "La frecuencia cardiaca debe ser mayor que 0")
    private Integer frecuenciaCardiaca;

    @NotNull(message = "La presión sistólica es obligatoria")
    @Min(value = 1, message = "La presión sistólica debe ser mayor que 0")
    private Integer presionSistolica;

    @NotNull(message = "La presión diastólica es obligatoria")
    @Min(value = 1, message = "La presión diastólica debe ser mayor que 0")
    private Integer presionDiastolica;

    @NotNull(message = "La saturación de O2 es obligatoria")
    @Min(value = 1, message = "La saturación de O2 debe ser mayor que 0")
    private Integer saturacionO2;

    private String observaciones;

    @NotNull(message = "El ID de la consulta es obligatorio")
    @Min(value = 1, message = "El ID de la consulta debe ser mayor a 0")
    private Long idConsulta;
    
    @NotNull(message = "La altura es obligatoria")
    private boolean publicado;

    public SignosVitalesRequestDto() {}

    public SignosVitalesRequestDto(LocalDateTime fechaHora, Double peso, Double altura, Double imc, Double temperatura,
            Integer frecuenciaCardiaca, Integer presionSistolica, Integer presionDiastolica, Integer saturacionO2,
            String observaciones, Long idConsulta, boolean publicado) {
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
        this.publicado = publicado;
    }

    // getters / setters
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

    public Long getIdConsulta() { return idConsulta; }
    public void setIdConsulta(Long idConsulta) { this.idConsulta = idConsulta; }

	public boolean isPublicado() {
		return publicado;
	}

	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}
    
}
