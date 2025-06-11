package com.imb2025.smedico.dto;

public class EncuestaRequestDTO {
     
	private Long pacienteId;
	private Long consultaId;
	private int puntaje;
	private String comentario;
	
	public EncuestaRequestDTO() {
		
	}
	public EncuestaRequestDTO(Long pacienteId, Long consultaId, int puntaje, String comentario) {
		super();
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
