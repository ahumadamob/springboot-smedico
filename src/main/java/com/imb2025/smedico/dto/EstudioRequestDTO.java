package com.imb2025.smedico.dto;

public class EstudioRequestDTO {
	
	private String nombre;
	
	private String descripcion;
	
	private Long pacientId;
	
	private Long medicoId;
	
	private Long especialidadId;
	
	private Long obraSocialId;

	private Long oredenEstudioId;
	
	private Long resultadoEstudioId;
	

	public EstudioRequestDTO(String nombre, String descripcion, Long pacientId, Long medicoId, Long especialidadId,
			Long obraSocialId, Long oredenEstudioId, Long resultadoEstudioId) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.pacientId = pacientId;
		this.medicoId = medicoId;
		this.especialidadId = especialidadId;
		this.obraSocialId = obraSocialId;
		this.oredenEstudioId = oredenEstudioId;
		this.resultadoEstudioId = resultadoEstudioId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getPacientId() {
		return pacientId;
	}

	public void setPacientId(Long pacientId) {
		this.pacientId = pacientId;
	}

	public Long getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(Long medicoId) {
		this.medicoId = medicoId;
	}

	public Long getEspecialidadId() {
		return especialidadId;
	}

	public void setEspecialidadId(Long especialidadId) {
		this.especialidadId = especialidadId;
	}

	public Long getObraSocialId() {
		return obraSocialId;
	}

	public void setObraSocialId(Long obraSocialId) {
		this.obraSocialId = obraSocialId;
	}

	public Long getOredenEstudioId() {
		return oredenEstudioId;
	}

	public void setOredenEstudioId(Long oredenEstudioId) {
		this.oredenEstudioId = oredenEstudioId;
	}

	public Long getResultadoEstudioId() {
		return resultadoEstudioId;
	}

	public void setResultadoEstudioId(Long resultadoEstudioId) {
		this.resultadoEstudioId = resultadoEstudioId;
	}

	

	
}
