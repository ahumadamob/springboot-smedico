package com.imb2025.smedico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "encuestas")
public class Encuesta extends BaseEntity {

    

    @Column(length = 500)
    private String comentario;

    @Column(nullable = false)
    private int puntaje;

    public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    public Encuesta() { }

    public Encuesta( Paciente paciente, Consulta consulta, int puntaje, String comentario) {
       
        this.paciente = paciente;
        this.consulta = consulta;
        this.puntaje = puntaje;
        this.comentario = comentario;
    }

 
    public int getPuntaje() { return puntaje; }
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }
}
