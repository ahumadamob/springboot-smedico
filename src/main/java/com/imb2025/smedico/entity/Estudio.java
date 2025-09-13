package com.imb2025.smedico.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JoinColumn(name = "especialidad_id")
    @JsonIgnoreProperties("estudios")
    private Especialidad especialidad;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resultado_estudio_id")
    @JsonIgnoreProperties("estudios")
    private ResultadoEstudio resultadoEstudio;

    public Estudio() {}

    public Estudio(Long id, String nombre, String descripcion,
                   Especialidad especialidad, ResultadoEstudio resultadoEstudio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.especialidad = especialidad;
        this.resultadoEstudio = resultadoEstudio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }

    public ResultadoEstudio getResultadoEstudio() { return resultadoEstudio; }
    public void setResultadoEstudio(ResultadoEstudio resultadoEstudio) { this.resultadoEstudio = resultadoEstudio; }
}
