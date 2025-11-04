package com.imb2025.smedico.dto.response;

import java.time.LocalDate;

import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Paciente;


public class OrdenEstudioResponseDto {
	
		private Long id;
	    private Long version;
	    private LocalDate fecha;
	    private Paciente paciente;
	    private Medico medico;
	   	private Estudio estudio;

	   	private String codigoOrden;
	    
       private LocalDate fechaVigencia;

		public LocalDate getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(LocalDate fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

		public String getCodigoOrden() {
			return codigoOrden;
		}

		public void setCodigoOrden(String codigoOrden) {
			this.codigoOrden = codigoOrden;
		}

	public Paciente getPaciente() {
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}

		public Medico getMedico() {
			return medico;
		}

		public void setMedico(Medico medico) {
			this.medico = medico;
		}

		public Estudio getEstudio() {
			return estudio;
		}

		public void setEstudio(Estudio estudio) {
			this.estudio = estudio;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}




   





}
