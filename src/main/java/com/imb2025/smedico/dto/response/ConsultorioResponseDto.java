package com.imb2025.smedico.dto.response;

public class ConsultorioResponseDto {
	private Long id;
    private String nombre;
    private String ubicacion;
    private String identificadorLegible;
    private Long version;
    
    public ConsultorioResponseDto() {}

    public ConsultorioResponseDto(Long id, String nombre, String ubicacion, String identificadorLegible, Long version) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.identificadorLegible = identificadorLegible;
        this.version = version;
    }
    
    public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdentificadorLegible() {
		return identificadorLegible;
	}
	public void setIdentificadorLegible(String identificadorLegible) {
		this.identificadorLegible = identificadorLegible;
	}
			
	public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
  
}
