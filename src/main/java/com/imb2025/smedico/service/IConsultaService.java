package com.imb2025.smedico.service;


import com.imb2025.smedico.entity.Consulta;
import java.time.LocalDate;                 
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


	public interface IConsultaService {
	    List<Consulta> findAll();
	    Consulta findById(Long id);
	    Consulta create(Consulta nueva, Long turnoId);        // sin DTO
	    Consulta update(Long id, Consulta cambios, Long turnoId);
	    void deleteById(Long id);

	    Page<Consulta> findByFechaBetween(LocalDate desde, LocalDate hasta, Pageable pageable);
	    long countByPacienteId(Long pacienteId);
	    List<Consulta>findDescripcionCorta(String texto);
	}


