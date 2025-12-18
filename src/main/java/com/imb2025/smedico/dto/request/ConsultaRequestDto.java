package com.imb2025.smedico.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ConsultaRequestDto {

    @NotNull @PastOrPresent
    private LocalDate fecha;

    @NotNull @Positive
    private Long turnoId;

    @Min(1) @Max(480)
    private Integer duracionMin;

    @Size(max = 500)
    private String comentarios;

    public ConsultaRequestDto() {}

	public ConsultaRequestDto(@NotNull @PastOrPresent LocalDate fecha, @NotNull @Positive Long turnoId,
			@Min(1) @Max(480) Integer duracionMin, @Size(max = 500) String comentarios) {
		super();
		this.fecha = fecha;
		this.turnoId = turnoId;
		this.duracionMin = duracionMin;
		this.comentarios = comentarios;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Long getTurnoId() {
		return turnoId;
	}

	public void setTurnoId(Long turnoId) {
		this.turnoId = turnoId;
	}

	public Integer getDuracionMin() {
		return duracionMin;
	}

	public void setDuracionMin(Integer duracionMin) {
		this.duracionMin = duracionMin;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}



}
