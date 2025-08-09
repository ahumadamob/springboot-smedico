package com.imb2025.smedico.DTO;

import java.time.LocalDate;

public class AfiliacionRequestDTO {

	private Long numeroAfiliado ;
	private LocalDate fechaVigenciaDesde ;
	private LocalDate fechaHasta ;
	private Long idpaciente;	
	private Long idobra;
		
	public AfiliacionRequestDTO() {
	}

	public AfiliacionRequestDTO(Long numeroAfiliado, LocalDate fechaVigenciaDesde, LocalDate fechaHasta,
			Long idpaciente, Long idobra) {
		this.numeroAfiliado = numeroAfiliado;
		this.fechaVigenciaDesde = fechaVigenciaDesde;
		this.fechaHasta = fechaHasta;
		this.idpaciente = idpaciente;
		this.idobra = idobra;
	}
	
	public Long getNumeroAfiliado() {
		return numeroAfiliado;
	}
	public void setNumeroAfiliado(Long numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}
	public LocalDate getFechaVigenciaDesde() {
		return fechaVigenciaDesde;
	}
	public void setFechaVigenciaDesde(LocalDate fechaVigenciaDesde) {
		this.fechaVigenciaDesde = fechaVigenciaDesde;
	}
	public LocalDate getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Long getIdpaciente() {
		return idpaciente;
	}
	public void setIdpaciente(Long idpaciente) {
		this.idpaciente = idpaciente;
	}
	public Long getIdobra() {
		return idobra;
	}
	public void setIdobra(Long idobra) {
		this.idobra = idobra;
	}
	
	
}
