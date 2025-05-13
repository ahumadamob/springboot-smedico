package com.imb2025.smedico.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HorarioAtencionEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Long medicoId;
    private String diaSemana;
    private String horaInicio;
    private String horaFin;
    
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
	
	

}