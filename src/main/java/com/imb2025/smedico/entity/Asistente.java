package com.imb2025.smedico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "asistentes")
public class Asistente extends BaseEntity{


    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, unique = true, length = 20)
    private String telefono;

    @Column(nullable = false, unique = true)
    private Long dni;

    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column
    private Integer severidad;

    // Constructores
    public Asistente() {
    }

    public Asistente( String apellido, String nombre, String telefono, Long dni, String email, Integer severidad) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dni = dni;
        this.email = email;
        this.severidad = severidad;
    }

    // Getters y Setters

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Integer getSeveridad() {
		return severidad;
	}

	public void setSeveridad(Integer severidad) {
		this.severidad = severidad;
	}

	// toString() útil para depuración y logs
    @Override
    public String toString() {
        return "Asistente{" +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", dni=" + dni +
                ", email='" + email + '\'' +
                ", piso='" + severidad + '\'' +
                '}';
    }

}
