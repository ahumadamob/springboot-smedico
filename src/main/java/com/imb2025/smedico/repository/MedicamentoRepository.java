package com.imb2025.smedico.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
	
    List<Medicamento> findByNombre(String nombre);
    
    Long countByPresentacion(String presentacion);

}
