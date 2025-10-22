package com.imb2025.smedico.dto.response;

import java.time.LocalDate;

import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.Receta.Estado;



public class RecetaResponseDto {
	
    private LocalDate fecha;
    private String observaciones;
    private Medico medico;
    private Paciente paciente;
    
    private Long id;
    private Long version;
    private Estado estado;

    
    
	public LocalDate getFecha() {
		return fecha;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
    
    


}
