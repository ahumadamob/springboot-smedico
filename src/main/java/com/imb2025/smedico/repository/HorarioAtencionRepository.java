package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.HorarioAtencion;

public interface HorarioAtencionRepository extends JpaRepository<HorarioAtencion, Long> {
	java.util.List<HorarioAtencion> findByDiaSemana(String diaSemana);

	long countByMedico_Id(Long medicoId);
}
