package com.imb2025.smedico.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class DiagnosticoRequestDto {

    @NotNull(message = "El id de la consulta es obligatorio")
    @Positive(message = "El id de la consulta debe ser un número positivo")
    private Long consultaId;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
    private String descripcion;

    @NotNull(message = "La fecha de diagnóstico es obligatoria")
    @PastOrPresent(message = "La fecha de diagnóstico no puede ser en el futuro")
    private LocalDate fechaDiagnostico;
    
    @NotNull (message = "El código de referencia no puede ser nulo")   
    @NotBlank(message = "El código de referencia es obligatorio")
    private String codigoReferencia;

    public DiagnosticoRequestDto() { }

    public DiagnosticoRequestDto(Long consultaId, String descripcion, LocalDate fechaDiagnostico, String codigoReferencia) {
        this.consultaId = consultaId;
        this.descripcion = descripcion;
        this.fechaDiagnostico = fechaDiagnostico;
        this.codigoReferencia = codigoReferencia;
    }

    public Long getConsultaId() { return consultaId; }
    public void setConsultaId(Long consultaId) { this.consultaId = consultaId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaDiagnostico() { return fechaDiagnostico; }
    public void setFechaDiagnostico(LocalDate fechaDiagnostico) { this.fechaDiagnostico = fechaDiagnostico; }

	public String getCodigoReferencia() {
		return codigoReferencia;
	}

	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
    
    
}
