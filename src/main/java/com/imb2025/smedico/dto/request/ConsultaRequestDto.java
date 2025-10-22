package com.imb2025.smedico.dto.request;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

public class ConsultaRequestDto {

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha;

    @NotNull(message = "El turno es obligatorio")
    @Positive(message = "El id de turno debe ser positivo")
    private Long turnoId;

    @Min(value = 1, message = "La duración debe ser al menos 1 minuto")
    @Max(value = 480, message = "La duración no puede superar 480 minutos")
    private Integer duracionMin;

    @Size(max = 500, message = "Los comentarios no pueden superar los 500 caracteres")
    private String comentarios;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaVigencia;

    // ✅ NUEVO: Identificador legible
    @NotBlank(message = "El identificador legible es obligatorio")
    @Size(max = 100, message = "El identificador legible no puede superar los 100 caracteres")
    private String identificadorLegible;

    // Constructores
    public ConsultaRequestDto() {}

    public ConsultaRequestDto(LocalDate fecha, Long turnoId, Integer duracionMin, 
                              String comentarios, LocalDate fechaVigencia, 
                              String identificadorLegible) {
        this.fecha = fecha;
        this.turnoId = turnoId;
        this.duracionMin = duracionMin;
        this.comentarios = comentarios;
        this.fechaVigencia = fechaVigencia;
        this.identificadorLegible = identificadorLegible;
    }

    // Getters y Setters
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Long getTurnoId() { return turnoId; }
    public void setTurnoId(Long turnoId) { this.turnoId = turnoId; }

    public Integer getDuracionMin() { return duracionMin; }
    public void setDuracionMin(Integer duracionMin) { this.duracionMin = duracionMin; }

    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

    public LocalDate getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(LocalDate fechaVigencia) { this.fechaVigencia = fechaVigencia; }

    // ✅ NUEVO: Getter y Setter para identificadorLegible
    public String getIdentificadorLegible() { return identificadorLegible; }
    public void setIdentificadorLegible(String identificadorLegible) { this.identificadorLegible = identificadorLegible; }
}