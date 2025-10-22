package com.imb2025.smedico.dto.response;

import java.time.LocalDate;

public class AfiliacionResponseDto {
    private Long id;
    private Long numeroAfiliado;
    private LocalDate fechaVigenciaDesde;
    private LocalDate fechaHasta;
    private Long idPaciente;
    private Long idObra;
    private Long version;     // requerido por la consigna
    private Boolean activa;   // <-- NUEVO: para el Ejercicio 1

    public AfiliacionResponseDto() {}

    public AfiliacionResponseDto(Long id,
                                 Long numeroAfiliado,
                                 LocalDate fechaVigenciaDesde,
                                 LocalDate fechaHasta,
                                 Long idPaciente,
                                 Long idObra,
                                 Long version,
                                 Boolean activa) {
        this.id = id;
        this.numeroAfiliado = numeroAfiliado;
        this.fechaVigenciaDesde = fechaVigenciaDesde;
        this.fechaHasta = fechaHasta;
        this.idPaciente = idPaciente;
        this.idObra = idObra;
        this.version = version;
        this.activa = activa;
    }

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

    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }

    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
}
