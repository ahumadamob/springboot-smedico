package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.Factura;

import java.time.LocalDate;
import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findAllByPacienteId(Long id);

    Long countByMedioPagoNombreIgnoreCase(String medioPago);
    
    List<Factura> findByFechaArchivadoIsNull(LocalDate fechaArchivado);
    
    List<Factura> findByFechaArchivadoIsNotNull(LocalDate fechaArchivado);
}
