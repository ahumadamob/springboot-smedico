package com.imb2025.smedico.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "consulta")
public class Consulta {

	
	   @Version                      
	    @Column(name = "version")
	    private Integer version;   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turno_id", nullable = false, unique = true)
    private Turno turno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(name = "duracion_min", nullable = false)
    private int duracionMin;

    @Column(length = 500)
    private String comentarios;

    // getters/setters
    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }
    public Consulta() {}

    public Consulta(Long id, LocalDate fecha, Turno turno, int duracionMin, String comentarios) {
        this.id = id;
        this.fecha = fecha;
        this.turno = turno;
        this.duracionMin = duracionMin;
        this.comentarios = comentarios;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Turno getTurno() { return turno; }
    public void setTurno(Turno turno) { this.turno = turno; }

    public int getDuracionMin() { return duracionMin; }
    public void setDuracionMin(int duracionMin) { this.duracionMin = duracionMin; }

    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
}
