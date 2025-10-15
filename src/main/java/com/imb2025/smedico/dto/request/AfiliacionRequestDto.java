package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

public class AfiliacionRequestDto {

    @NotNull(message = "El número de afiliado es obligatorio") // CAMBIO
    @Min(value = 1, message = "El número de afiliado debe ser positivo") // CAMBIO
    private Long numeroAfiliado;

    @NotNull(message = "La fecha de vigencia es obligatoria") // CAMBIO
    @PastOrPresent(message = "La fecha de vigencia desde debe ser pasada o presente") // CAMBIO
    private LocalDate fechaVigenciaDesde;

    @NotNull(message = "La fecha de vigencia hasta es obligatoria") // CAMBIO
    @FutureOrPresent(message = "La fecha de vigencia hasta debe ser presente o futura") // CAMBIO
    private LocalDate fechaHasta;

    @NotNull(message = "El ID del paciente es obligatorio") // CAMBIO
    @Min(value = 1, message = "El ID del paciente debe ser mayor a 0") // CAMBIO
    private Long idpaciente;

    @NotNull(message = "El ID de la obra social es obligatorio") // CAMBIO
    @Min(value = 1, message = "El ID de la obra debe ser mayor a 0") // CAMBIO
    private Long idobra;

    public AfiliacionRequestDto() {}

    public AfiliacionRequestDto(Long numeroAfiliado, LocalDate fechaVigenciaDesde, LocalDate fechaHasta,
                                Long idpaciente, Long idobra) {
        this.numeroAfiliado = numeroAfiliado;
        this.fechaVigenciaDesde = fechaVigenciaDesde;
        this.fechaHasta = fechaHasta;
        this.idpaciente = idpaciente;
        this.idobra = idobra;
    }

    public Long getNumeroAfiliado() { return numeroAfiliado; }
    public void setNumeroAfiliado(Long numeroAfiliado) { this.numeroAfiliado = numeroAfiliado; }

    public LocalDate getFechaVigenciaDesde() { return fechaVigenciaDesde; }
    public void setFechaVigenciaDesde(LocalDate fechaVigenciaDesde) { this.fechaVigenciaDesde = fechaVigenciaDesde; }

    public LocalDate getFechaHasta() { return fechaHasta; }
    public void setFechaHasta(LocalDate fechaHasta) { this.fechaHasta = fechaHasta; }

    public Long getIdpaciente() { return idpaciente; }
    public void setIdpaciente(Long idpaciente) { this.idpaciente = idpaciente; }

    public Long getIdobra() { return idobra; }
    public void setIdobra(Long idobra) { this.idobra = idobra; }
}
