package com.imb2025.smedico.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.validation.constraints.NotNull;

public class TurnoRequestDTO {
	
	 	private LocalDate fecha;
	    private LocalTime hora;
	    private Long pacienteId;
	    private Long medicoId;
	    private Long estadoTurnoId;

	    public TurnoRequestDTO(LocalDate fecha, LocalTime hora, Long pacienteId, Long medicoId, Long estadoTurnoId) {
	        super();
	    	this.fecha = fecha;
	        this.hora = hora;
	        this.pacienteId = pacienteId;
	        this.medicoId = medicoId;
	        this.estadoTurnoId = estadoTurnoId;
	    }

	    public LocalDate getFecha() {
	        return fecha;
	    }

	    public void setFecha(LocalDate fecha) {
	        this.fecha = fecha;
	    }

	    public LocalTime getHora() {
	        return hora;
	    }

	    public void setHora(LocalTime hora) {
	        this.hora = hora;
	    }

	    public Long getPacienteId() {
	        return pacienteId;
	    }

	    public void setPacienteId(Long pacienteId) {
	        this.pacienteId = pacienteId;
	    }

	    public Long getMedicoId() {
	        return medicoId;
	    }

	    public void setMedicoId(Long medicoId) {
	        this.medicoId = medicoId;
	    }

	    public Long getEstadoTurnoId() {
	        return estadoTurnoId;
	    }

	    public void setEstadoTurnoId(Long estadoTurnoId) {
	        this.estadoTurnoId = estadoTurnoId;
	    }
	
}
