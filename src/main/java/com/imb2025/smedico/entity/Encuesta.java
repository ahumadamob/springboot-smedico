package com.imb2025.smedico.entity;
import com.imb2025.smedico.entity.Consulta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;




@Entity
public class Encuesta {
       
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String comentario;
	private int puntaje;
	
	//Relaciones paciente
	@ManyToOne	
	private Paciente paciente;
	
	@ManyToOne	
	private Consulta consulta;
	
	
	
    //Constructores
	public Encuesta() {
		
	}
	
	public Encuesta(Long id, Paciente paciente, Consulta consulta, int puntaje, String comentario) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.consulta = consulta;
		this.puntaje = puntaje;
		this.comentario = comentario;
	}
	
	//GET - SET
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
}
