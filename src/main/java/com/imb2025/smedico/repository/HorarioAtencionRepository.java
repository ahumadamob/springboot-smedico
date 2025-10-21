package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.HorarioAtencion;
import java.util.List;

public interface HorarioAtencionRepository extends JpaRepository<HorarioAtencion, Long> {

    List<HorarioAtencion> findByActivoTrue();
    List<HorarioAtencion> findByActivoFalse();

}
