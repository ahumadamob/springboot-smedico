package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "horarioatencion") master
@Entity
public class HorarioAtencion{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private Long medicoId;
   private String diaSemana;
    private String horaInicio;
    private String horaFin;
    

    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Turno turno;
    @ManyToOne
    private Consultorio consultorio;
    @ManyToOne
    private Asistente asistente;

    
    
	public HorarioAtencion() {

	}
	
	public HorarioAtencion(String diaSemana, String horaInicio, String horaFin, Medico medico, Turno turno,
			Consultorio consultorio, Asistente asistente) {
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.medico = medico;
		this.turno = turno;
		this.consultorio = consultorio;
		this.asistente = asistente;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	

	public long getMedicoId() {
		return medicoId;
	}
	public void setMedicoId(long medicoId) {
		this.medicoId = medicoId;
	}

	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Consultorio getConsultorio() {
		return consultorio;
	}
	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}
	public Asistente getAsistente() {
		return asistente;
	}
	public void setAsistente(Asistente asistente) {
		this.asistente = asistente;
	}

	
	

}