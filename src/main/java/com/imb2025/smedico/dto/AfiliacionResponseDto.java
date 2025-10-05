package com.imb2025.smedico.dto;

import java.time.LocalDate;

public class AfiliacionResponseDto {
    private Long id;
    private Long numeroAfiliado;
    private LocalDate fechaVigenciaDesde;
    private LocalDate fechaHasta;
    private Long idPaciente;
    private Long idObra;

    public AfiliacionResponseDto(Long id, Long numeroAfiliado,
                                 LocalDate fechaVigenciaDesde, LocalDate fechaHasta,
                                 Long idPaciente, Long idObra) {
        this.id = id;
        this.numeroAfiliado = numeroAfiliado;
        this.fechaVigenciaDesde = fechaVigenciaDesde;
        this.fechaHasta = fechaHasta;
        this.idPaciente = idPaciente;
        this.idObra = idObra;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getNumeroAfiliado() { return numeroAfiliado; }
    public void setNumeroAfiliado(Long numeroAfiliado) { this.numeroAfiliado = numeroAfiliado; }

    public LocalDate getFechaVigenciaDesde() { return fechaVigenciaDesde; }
    public void setFechaVigenciaDesde(LocalDate fechaVigenciaDesde) { this.fechaVigenciaDesde = fechaVigenciaDesde; }

    public LocalDate getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(LocalDate fechaHasta) { this.fechaHasta = fechaHasta; }

    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }

    public Long getIdObra() { return idObra; }
    public void setIdObra(Long idObra) { this.idObra = idObra; }
}
