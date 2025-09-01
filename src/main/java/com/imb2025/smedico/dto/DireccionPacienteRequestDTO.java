package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public class DireccionPacienteRequestDTO {

    @NotBlank(message = "La calle no puede estar vacía")
    @Size(min = 2, max = 100, message = "La calle debe tener entre 2 y 100 caracteres")
    private String calle;

    @NotNull(message = "El número es obligatorio")
    @Positive(message = "El número debe ser un valor positivo")
    private Long numero;

    @NotBlank(message = "La localidad no puede estar vacía")
    @Size(min = 2, max = 80, message = "La localidad debe tener entre 2 y 80 caracteres")
    private String localidad;

    @NotBlank(message = "La provincia no puede estar vacía")
    @Size(min = 2, max = 80, message = "La provincia debe tener entre 2 y 80 caracteres")
    private String provincia;

    @NotBlank(message = "El código postal no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z0-9]{3,10}$", message = "El código postal debe tener entre 3 y 10 caracteres alfanuméricos")
    private String ccpp;

    public DireccionPacienteRequestDTO() {}

    public DireccionPacienteRequestDTO(String calle, Long numero, String localidad, String provincia, String ccpp) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
        this.ccpp = ccpp;
    }

    // Getters y Setters
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Long getNumero() {
        return numero;
    }
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCcpp() {
        return ccpp;
    }
    public void setCcpp(String ccpp) {
        this.ccpp = ccpp;
    }
}

