package com.imb2025.smedico.entity;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private Turno turno;
    private int duracionMin;
    private String comentarios;

}

