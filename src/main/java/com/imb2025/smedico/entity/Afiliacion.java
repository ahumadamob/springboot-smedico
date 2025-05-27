package com.imb2025.smedico.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class Afiliacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long numeroAfiliado ;
	private LocalDate fechaVigenciaDesde ;
	private LocalDate fechaHasta ;
	@OneToOne
	private Paciente paciente;	
	@ManyToOne
	private ObraSocial obra;
	
	public Afiliacion() {
	}

	public Afiliacion(Long numeroAfiliado, LocalDate fechaVigenciaDesde, LocalDate fechaHasta, Paciente paciente,
			ObraSocial obra) {
		this.numeroAfiliado = numeroAfiliado;
		this.fechaVigenciaDesde = fechaVigenciaDesde;
		this.fechaHasta = fechaHasta;
		this.paciente = paciente;
		this.obra = obra;
	}
	private Long pacienteId;	
	private String obraSocialId ;
	private Long numeroAfiliado ;
	private LocalDate fechaVigenciaDesde ;
	private LocalDate fechaHasta ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
  
	public Long getPacienteId() {
		return pacienteId;
	}
	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}
	public String getObraSocialId() {
		return obraSocialId;
	}
	public void setObraSocialId(String obraSocialId) {
		this.obraSocialId = obraSocialId;
	}
  
	public Long getNumeroAfiliado() {
		return numeroAfiliado;
	}
	public void setNumeroAfiliado(Long numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}
	public LocalDate getFechaVigenciaDesde() {
		return fechaVigenciaDesde;
	}
	public void setFechaVigenciaDesde(LocalDate fechaVigenciaDesde) {
		this.fechaVigenciaDesde = fechaVigenciaDesde;
	}
	public LocalDate getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
  
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public ObraSocial getObra() {
		return obra;
	}
	public void setObra(ObraSocial obra) {
		this.obra = obra;
	}

}
