package com.imb2025.smedicoDtoRequest;



public class HorarioAtencionRequestDTO {

    private String diaSemana;
    private String horaInicio;
    private String horaFin;
    private Long medicoId;
    private Long turnoId;
    private Long consultorioId;
    private Long asistenteId;
    
    
	public HorarioAtencionRequestDTO(String diaSemana, String horaInicio, String horaFin, Long medicoId, Long turnoId,
			Long consultorioId, Long asistenteId) {
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.medicoId = medicoId;
		this.turnoId = turnoId;
		this.consultorioId = consultorioId;
		this.asistenteId = asistenteId;
	}
	
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public Long getMedicoId() {
		return medicoId;
	}
	public void setMedicoId(Long medicoId) {
		this.medicoId = medicoId;
	}
	public Long getTurnoId() {
		return turnoId;
	}
	public void setTurnoId(Long turnoId) {
		this.turnoId = turnoId;
	}
	public Long getConsultorioId() {
		return consultorioId;
	}
	public void setConsultorioId(Long consultorioId) {
		this.consultorioId = consultorioId;
	}
	public Long getAsistenteId() {
		return asistenteId;
	}
	public void setAsistenteId(Long asistenteId) {
		this.asistenteId = asistenteId;
	}
    
    
}
