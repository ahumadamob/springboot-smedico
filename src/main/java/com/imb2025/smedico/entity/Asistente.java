package com.imb2025.smedico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "asistentes") // Nombre explícito para la tabla
public class Asistente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    // Constructores
    public Asistente() {
    }

    public Asistente(Long id, String apellido, String nombre, String telefono, Long dni, String email) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dni = dni;
        this.email = email;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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

    // toString() útil para depuración y logs
    @Override
    public String toString() {
        return "Asistente{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", dni=" + dni +
                ", email='" + email + '\'' +
                '}';
    }
}
