package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.imb2025.smedico.entity.HorarioAtencion; 

@Repository
public interface HorarioAtencionRepository extends JpaRepository<HorarioAtencion, Long> {
   
}