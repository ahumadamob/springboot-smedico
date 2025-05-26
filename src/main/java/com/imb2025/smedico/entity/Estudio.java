package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "estudio")
@Entity
public class Estudio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String descripcion;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "especialidad_id")
	private Especialidad especialidad;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obra_social_id")
	private ObraSocial obraSocial;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orden_estudio_id")
	private OrdenEstudio oredenEstudio;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resultado_estudio_id")
	private ResultadoEstudio resultadoEstudio;
	
	public Estudio() {};
	
	public Estudio(String nombre, String descripcion, Paciente paciente, Medico medico,
			Especialidad especialidad, ObraSocial obraSocial, OrdenEstudio oredenEstudio,
			ResultadoEstudio resultadoEstudio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.paciente = paciente;
		this.medico = medico;
		this.especialidad = especialidad;
		this.obraSocial = obraSocial;
		this.oredenEstudio = oredenEstudio;
		this.resultadoEstudio = resultadoEstudio;
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
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	public ObraSocial getObraSocial() {
		return obraSocial;
	}
	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}
	public OrdenEstudio getOredenEstudio() {
		return oredenEstudio;
	}
	public void setOredenEstudio(OrdenEstudio oredenEstudio) {
		this.oredenEstudio = oredenEstudio;
	}
	public ResultadoEstudio getResultadoEstudio() {
		return resultadoEstudio;
	}
	public void setResultadoEstudio(ResultadoEstudio resultadoEstudio) {
		this.resultadoEstudio = resultadoEstudio;
	}
	
	
}
