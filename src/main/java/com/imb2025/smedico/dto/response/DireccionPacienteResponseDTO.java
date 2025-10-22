package com.imb2025.smedico.dto.response;

public class DireccionPacienteResponseDTO {

    private Long id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
    private String identificadorLegible;
    private Long version;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getIdentificadorLegible() { return identificadorLegible; }
    public void setIdentificadorLegible(String identificadorLegible) { this.identificadorLegible = identificadorLegible; }

    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}
