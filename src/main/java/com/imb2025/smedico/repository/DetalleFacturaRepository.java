package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.DetalleFactura;
import java.util.List;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {
    List<DetalleFactura> findByDescripcionContainingIgnoreCase(String descripcion);
    long countByFactura_Id(Long facturaId);
}
