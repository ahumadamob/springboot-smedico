
package com.imb2025.smedico.entity;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "obras_sociales")
public class ObraSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificadorLegible;
    private String nombre;
    private String telefono;
    private String direccion;
    private String cobertura;
    
    @Column(nullable = false)
    private LocalDate fechaVigencia;

    @Version
    private int version;

    // 🔹 Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentificadorLegible() { return identificadorLegible; }
    public void setIdentificadorLegible(String identificadorLegible) { this.identificadorLegible = identificadorLegible; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCobertura() { return cobertura; }
    public void setCobertura(String cobertura) { this.cobertura = cobertura; }

    public LocalDate getFechaVigencia() { return fechaVigencia; }
    public void setFechaVigencia(LocalDate fechaVigencia) { this.fechaVigencia = fechaVigencia; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
}
