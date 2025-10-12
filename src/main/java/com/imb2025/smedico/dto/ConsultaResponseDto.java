
	package com.imb2025.smedico.dto;

	import java.time.LocalDate;

	public class ConsultaResponseDto {

	    private Long id;
	    private LocalDate fecha;
	    private int duracionMin;
	    private String comentarios;
	    private Long turnoId;

	    public ConsultaResponseDto() {}

	    public ConsultaResponseDto(Long id, LocalDate fecha, int duracionMin, String comentarios, Long turnoId) {
	        this.id = id;
	        this.fecha = fecha;
	        this.duracionMin = duracionMin;
	        this.comentarios = comentarios;
	        this.turnoId = turnoId;
	    }

	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public LocalDate getFecha() { return fecha; }
	    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

	    public int getDuracionMin() { return duracionMin; }
	    public void setDuracionMin(int duracionMin) { this.duracionMin = duracionMin; }

	    public String getComentarios() { return comentarios; }
	    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

	    public Long getTurnoId() { return turnoId; }
	    public void setTurnoId(Long turnoId) { this.turnoId = turnoId; }
	}


