package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.TurnoRequestDTO;
import com.imb2025.smedico.entity.Turno;
import jakarta.validation.constraints.NotNull;

public interface ITurnoService {
	 List<Turno> findAll();
	    Turno findById(Long id);
	    Turno create(Turno turno) throws Exception;
	    Turno update(Long id, Turno turno) throws Exception; 
	    void deleteById(Long id);

}
