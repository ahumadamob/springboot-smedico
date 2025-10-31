package com.imb2025.smedico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estudio")
public class Estudio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 500, nullable = false)
    private String descripcion;

    // Muchas estudios pertenecen a una especialidad
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

    // Relación 1–1 unidireccional: la FK está en Estudio
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "resultado_estudio_id", unique = true)
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
