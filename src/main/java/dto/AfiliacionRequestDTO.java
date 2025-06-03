
package dto;

import java.time.LocalDate;

/**
 *
 * @author maxim
 */
public class AfiliacionRequestDTO {
    
    private Long pacienteId;
    private Long obraSocialId;
    private Long numeroAfiliado;
    private LocalDate fechaVigenciaDesde;

    public AfiliacionRequestDTO(Long pacienteId, Long obraSocialId, Long numeroAfiliado, LocalDate fechaVigenciaDesde) {
        this.pacienteId = pacienteId;
        this.obraSocialId = obraSocialId;
        this.numeroAfiliado = numeroAfiliado;
        this.fechaVigenciaDesde = fechaVigenciaDesde;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getObraSocialId() {
        return obraSocialId;
    }

    public void setObraSocialId(Long obraSocialId) {
        this.obraSocialId = obraSocialId;
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
}
