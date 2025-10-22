package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.DetalleFactura;
import java.util.List;

import java.time.LocalDate;
import java.util.List;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {
    List<DetalleFactura> findByDescripcionContainingIgnoreCase(String descripcion);
    long countByFactura_Id(Long facturaId);

    // 🔹 Nuevos métodos
    List<DetalleFactura> findByFechaVigenciaGreaterThanEqual(LocalDate fecha);
    List<DetalleFactura> findByFechaVigenciaLessThan(LocalDate fecha);
}
