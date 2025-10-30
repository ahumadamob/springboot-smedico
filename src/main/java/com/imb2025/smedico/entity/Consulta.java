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


	    public Consulta() {}

	    

		public Consulta(LocalDate fecha, Turno turno, Integer duracionMin, String comentarios) {
			super();
			this.fecha = fecha;
			this.turno = turno;
			this.duracionMin = duracionMin;
			this.comentarios = comentarios;
		}



		public LocalDate getFecha() {
			return fecha;
		}


		public void setFecha(LocalDate fecha) {
			this.fecha = fecha;
		}


		public Turno getTurno() {
			return turno;
		}


		public void setTurno(Turno turno) {
			this.turno = turno;
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
	    
	    
}