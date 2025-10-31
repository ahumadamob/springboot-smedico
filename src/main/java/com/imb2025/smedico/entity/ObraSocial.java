package com.imb2025.smedico.entity;

import jakarta.persistence.*;

/**
 * Entidad que representa una obra social en el sistema.
 */
@Entity
@Table(name = "obrasocial")
public class ObraSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(length = 20)
    private String telefono;

    @Column(length = 150)
    private String direccion;

    @Column(length = 100)
    private String cobertura;

    public ObraSocial() {}

    public ObraSocial(String nombre, String telefono, String direccion, String cobertura) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cobertura = cobertura;
    }

    public Long getId() {
        return id;
    }

    /**
     * Setter del ID.
     * Nota: generalmente no se utiliza ya que el ID es autogenerado por la base de datos.
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCobertura() {
        return cobertura;
    }
    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }
}


