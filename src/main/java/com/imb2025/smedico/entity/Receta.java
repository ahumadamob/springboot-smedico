package com.imb2025.smedico.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Receta extends BaseEntity{


    @Column(nullable = false)
    private LocalDate fecha;

    @Column(length = 500)
    private String observaciones;


    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;
    
    public enum Estado {
        ACTIVO,
        INACTIVO
    }
    public Receta() {}

    public Receta( LocalDate fecha, String observaciones, Medico medico, Paciente paciente) {
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.medico = medico;
        this.paciente = paciente;
    }
    


    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
