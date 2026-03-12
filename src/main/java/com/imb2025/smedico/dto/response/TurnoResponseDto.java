package com.imb2025.smedico.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoResponseDto {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String pacienteNombre;
    private String medicoNombre;
    private String estadoTurnoDescripcion;
    private Long version;
    
    /*Ejercicio Final*/
    private int severidad;
    
    public TurnoResponseDto() {}

    public TurnoResponseDto(Long id, LocalDate fecha, LocalTime hora,
                            String pacienteNombre, String medicoNombre,
                            String estadoTurnoDescripcion, Long version,int severidad) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.pacienteNombre = pacienteNombre;
        this.medicoNombre = medicoNombre;
        this.estadoTurnoDescripcion = estadoTurnoDescripcion;
        this.version = version;
        this.severidad = severidad;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEstadoTurnoDescripcion() {
		return estadoTurnoDescripcion;
	}

	public void setEstadoTurnoDescripcion(String estadoTurnoDescripcion) {
		this.estadoTurnoDescripcion = estadoTurnoDescripcion;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	/*Ejercicio Final*/
	public int getSeveridad() {
		return severidad;
	}

	public void setSeveridad(int severidad) {
		this.severidad = severidad;
	}

    
}
