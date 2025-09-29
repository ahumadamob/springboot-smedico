package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.MedicoRequestDto;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import java.util.List;

public interface IMedicoService {

    List<Medico> findAll();

    Medico create(Medico medico);

    Medico update(Long id, Medico medico) throws ResourceNotFoundException;

    Medico findById(Long id) throws ResourceNotFoundException;

    boolean existsById(Long id);

    void deleteById(Long id) throws ResourceNotFoundException;

    Medico fromDto(MedicoRequestDto medicoRequestDto) throws ResourceNotFoundException, IllegalArgumentException;
}
