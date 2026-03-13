package com.imb2025.smedico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.MedioPago;

public interface MedioPagoRepository extends JpaRepository<MedioPago, Long> {
	
	List<MedioPago> findByTipo(MedioPago.TipoPago tipo);//a

    Long countByNombre(String nombre);//a
    
    
    
    List<MedioPago> findByPublicadoTrue(Long id, MedioPago medioPago);
    
    List<MedioPago> findByPublicadoFalse(Long id, MedioPago medioPago);
    
    MedioPago findByIdPublicados(Long id);


}
