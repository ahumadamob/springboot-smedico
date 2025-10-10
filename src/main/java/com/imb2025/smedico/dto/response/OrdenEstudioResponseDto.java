package com.imb2025.smedico.dto.response;

import java.time.LocalDate;

import com.imb2025.smedico.entity.Estudio;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public class OrdenEstudioResponseDto {
	
		private Long id;
	    private Long version;

	     @FutureOrPresent(message = "La fecha debe ser hoy o una fecha futura")
	    private LocalDate fecha;
	    
	    @NotNull(message="El campo medicoId es obligatorio")
	    private Long medicoId;

	    @NotNull(message="El campo pacienteId es obligatorio")
	    private Long pacienteId;


		@NotNull(message="El campo estudioId es obligatorio")
	    private Long estudioId;
	    
	    

	



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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Long getMedicoId() {
		return medicoId;
	}


	public Long getPacienteId() {
		return pacienteId;
	}


	public Long getEstudioId() {
		return estudioId;
	}
	
	public void setMedicoId(Long medicoId) {
	    this.medicoId = medicoId;
	}

    public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public void setEstudioId(Long estudioId) {
		this.estudioId = estudioId;
	}

   





}
