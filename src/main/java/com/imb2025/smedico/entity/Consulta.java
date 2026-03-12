package com.imb2025.smedico.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "consulta")
public class Consulta extends BaseEntity {

    @Column(nullable = false)
    private LocalDate fecha;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turno_id", nullable = false, unique = true)
    private Turno turno;

    @Column(name = "duracion_min", nullable = false)
    private Integer duracionMin;

    @Column(length = 500)
    private String comentarios;

    @Column(name = "descripcionCorta")
    private String descripcionCorta;
    public Consulta() {}

   

    public Consulta(LocalDate fecha, Turno turno, Integer duracionMin, String comentarios,String descripcionCorta) {
		super();
		this.fecha = fecha;
		this.turno = turno;
		this.duracionMin = duracionMin;
		this.comentarios = comentarios;
		this.descripcionCorta = descripcionCorta;
	}



	public String getDescripcionCorta() {
		return descripcionCorta;
	}



	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}



	public void setDuracionMin(Integer duracionMin) {
		this.duracionMin = duracionMin;
	}



	public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Turno getTurno() { return turno; }
    public void setTurno(Turno turno) { this.turno = turno; }

    public int getDuracionMin() { return duracionMin; }
    public void setDuracionMin(int duracionMin) { this.duracionMin = duracionMin; }

    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
}
