package com.imb2025.smedico.dto.response.TurnoResponseDto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoResponseDto {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String pacienteNombre;
    private String medicoNombre;
    private String estadoTurnoDescripcion;
    private Integer version;

    public TurnoResponseDto() {}

    public TurnoResponseDto(Long id, LocalDate fecha, LocalTime hora,
                            String pacienteNombre, String medicoNombre,
                            String estadoTurnoDescripcion, Integer version) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.pacienteNombre = pacienteNombre;
        this.medicoNombre = medicoNombre;
        this.estadoTurnoDescripcion = estadoTurnoDescripcion;
        this.version = version;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

    
}
