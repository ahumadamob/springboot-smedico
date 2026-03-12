
	package com.imb2025.smedico.dto.response;

	import java.time.LocalDate;

	public class ConsultaResponseDto {
	    private Long id;
	    private LocalDate fecha;
	    private Integer duracionMin;
	    private String comentarios;
	    private Long turnoId;
	    private Long version;
	    private String descripcionCorta;
	    public ConsultaResponseDto() {}

	    public ConsultaResponseDto(Long id, LocalDate fecha, Integer duracionMin,
	                               String comentarios, Long turnoId, Long version,String descripcionCorta) {
	        this.id = id;
	        this.fecha = fecha;
	        this.duracionMin = duracionMin;
	        this.comentarios = comentarios;
	        this.turnoId = turnoId;
	        this.version = version;
	        this.descripcionCorta = descripcionCorta;
	    }

		public String getDescripcionCorta() {
			return descripcionCorta;
		}

		public void setDescripcionCorta(String descripcionCorta) {
			this.descripcionCorta = descripcionCorta;
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

		public Integer getDuracionMin() {
			return duracionMin;
		}

		public void setDuracionMin(Integer duracionMin) {
			this.duracionMin = duracionMin;
		}

		public String getComentarios() {
			return comentarios;
		}

		public void setComentarios(String comentarios) {
			this.comentarios = comentarios;
		}

		public Long getTurnoId() {
			return turnoId;
		}

		public void setTurnoId(Long turnoId) {
			this.turnoId = turnoId;
		}

		public Long getVersion() {
			return version;
		}

		public void setVersion(Long version) {
			this.version = version;
		}

	}



