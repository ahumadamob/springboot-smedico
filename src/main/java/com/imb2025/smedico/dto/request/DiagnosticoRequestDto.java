package com.imb2025.smedico.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public class DiagnosticoRequestDto {

    @NotNull(message = "El id de la consulta es obligatorio")
    @Positive(message = "El id de la consulta debe ser positivo")
    private Long consultaId;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotNull(message = "La fecha de diagnóstico es obligatoria")
    @PastOrPresent(message = "La fecha de diagnóstico no puede ser futura")
    private LocalDate fechaDiagnostico;

    // Ejercicio 2
    @NotBlank(message = "El identificadorLegible es obligatorio")
    private String identificadorLegible;

    // Ejercicio 3
    @NotNull(message = "La fechaVigencia es obligatoria")
    private LocalDate fechaVigencia;

    public DiagnosticoRequestDto() { }

    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDate getFechaDiagnostico() { return fechaDiagnostico; }
    public void setFechaDiagnostico(LocalDate fechaDiagnostico) { this.fechaDiagnostico = fechaDiagnostico; }
    public String getIdentificadorLegible() { return identificadorLegible; }
    public void setIdentificadorLegible(String identificadorLegible) { this.identificadorLegible = identificadorLegible; }
    public LocalDate getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(LocalDate fechaVigencia) { this.fechaVigencia = fechaVigencia; }
}
