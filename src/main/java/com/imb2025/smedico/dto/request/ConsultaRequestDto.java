package com.imb2025.smedico.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

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
    // Getters y Setters
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Long getTurnoId() { return turnoId; }
    public void setTurnoId(Long turnoId) { this.turnoId = turnoId; }

    public Integer getDuracionMin() { return duracionMin; }
    public void setDuracionMin(Integer duracionMin) { this.duracionMin = duracionMin; }

    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

  
}