package com.imb2025.smedico.dto;

import java.time.LocalDate;

public class EstudioResponseDTO {

	private Long id;
	private String nombre;
	private String descripcion;
	private String pacienteNombre;
    private String medicoNombre;
    private String especialidadNombre;
    private String obraSocialNombre;
    private LocalDate ordenFecha;
    private String resultadoDescripcion;
    
    public EstudioResponseDTO(com.imb2025.smedico.entity.Estudio estudio) {
        this.id = estudio.getId();
        this.nombre = estudio.getNombre();
        this.descripcion = estudio.getDescripcion();
        this.pacienteNombre = estudio.getPaciente() != null ? estudio.getPaciente().getNombre() : null;
        this.medicoNombre = estudio.getMedico() != null ? estudio.getMedico().getNombre() : null;
        this.especialidadNombre = estudio.getEspecialidad() != null ? estudio.getEspecialidad().getNombre() : null;
        this.obraSocialNombre = estudio.getObraSocial() != null ? estudio.getObraSocial().getNombre() : null;
        this.ordenFecha = estudio.getOredenEstudio() != null ? estudio.getOredenEstudio().getFecha() : null;
        this.resultadoDescripcion = estudio.getResultadoEstudio() != null ? estudio.getResultadoEstudio().getObservaciones() : null;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPacienteNombre() {
		return pacienteNombre;
	}

	public void setPacienteNombre(String pacienteNombre) {
		this.pacienteNombre = pacienteNombre;
	}

	public String getMedicoNombre() {
		return medicoNombre;
	}

	public void setMedicoNombre(String medicoNombre) {
		this.medicoNombre = medicoNombre;
	}

	public String getEspecialidadNombre() {
		return especialidadNombre;
	}

	public void setEspecialidadNombre(String especialidadNombre) {
		this.especialidadNombre = especialidadNombre;
	}

	public String getObraSocialNombre() {
		return obraSocialNombre;
	}

	public void setObraSocialNombre(String obraSocialNombre) {
		this.obraSocialNombre = obraSocialNombre;
	}

	public LocalDate getOrdenFecha() {
		return ordenFecha;
	}

	public void setOrdenFecha(LocalDate ordenFecha) {
		this.ordenFecha = ordenFecha;
	}

	public String getResultadoDescripcion() {
		return resultadoDescripcion;
	}

	public void setResultadoDescripcion(String resultadoDescripcion) {
		this.resultadoDescripcion = resultadoDescripcion;
	}

    
}
