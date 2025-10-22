// ============================
// 1) DTO: EncuestaResponseDto
// ============================
package com.imb2025.smedico.dto.response;

public class EncuestaResponseDto {

    private Long id;
    private Long version;
    private String comentario;
    private int puntaje;

    // Solo IDs (evita LazyInitialization al serializar)
    private Long consultaId;
    private Long pacienteId;
    //Tp-parcial
    private String estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public int getPuntaje() { return puntaje; }
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; }

    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
	
    public String getEstado() {return estado;}
    public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
