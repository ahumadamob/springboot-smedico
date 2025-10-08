package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByTurno_Id(Long turnoId);
//.
    boolean existsByTurno_IdAndIdNot(Long turnoId, Long id);
}
 