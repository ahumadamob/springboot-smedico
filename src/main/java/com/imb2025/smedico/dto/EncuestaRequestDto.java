package com.imb2025.smedico.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EncuestaRequestDto {
    
	@NotNull(message = "El paciente es obligatorio")
	private Long pacienteId;
	@NotNull(message = "El paciente debe tener una consulta medica")
	private Long consultaId;
	@Min(value = 1, message = "El puntaje minimo es 1")
	private int puntaje;	
	@NotBlank(message = "El comentario es obligatorio")
	private String comentario;

        public EncuestaRequestDto() {}

        public EncuestaRequestDto(
                Long pacienteId, Long consultaId, int puntaje, String comentario) {
                this.pacienteId = pacienteId;
                this.consultaId = consultaId;
                this.puntaje = puntaje;
                this.comentario = comentario;
        }

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Long getConsultaId() {
		return consultaId;
	}

	public void setConsultaId(Long consultaId) {
		this.consultaId = consultaId;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}



}
