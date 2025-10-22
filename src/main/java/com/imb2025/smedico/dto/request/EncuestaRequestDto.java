package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class EncuestaRequestDto {

    @NotNull(message = "El paciente es obligatorio")
    @Positive(message = "pacienteId debe ser un número positivo")
    private Long pacienteId;

    @NotNull(message = "La consulta médica es obligatoria")
    @Positive(message = "consultaId debe ser un número positivo")
    private Long consultaId;

    @NotNull(message = "El puntaje es obligatorio")
    @Min(value = 1, message = "El puntaje mínimo es 1")
    @Max(value = 5, message = "El puntaje máximo es 5")
    private Integer puntaje;

    @NotBlank(message = "El comentario es obligatorio")
    @Size(max = 500, message = "El comentario admite hasta 500 caracteres")
    private String comentario;
    
    @jakarta.validation.constraints.NotBlank(message = "estado es requerido")
    public String estado;

    public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public EncuestaRequestDto() {}

    public EncuestaRequestDto(Long pacienteId, Long consultaId, Integer puntaje, String comentario) {
        this.pacienteId = pacienteId;
        this.consultaId = consultaId;
        this.puntaje = puntaje;
        this.comentario = comentario;
    }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }

    public Integer getPuntaje() { return puntaje; }
    public void setPuntaje(Integer puntaje) { this.puntaje = puntaje; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

	
}
