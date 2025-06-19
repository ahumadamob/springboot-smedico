package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.MedicoRequestDTO;
import com.imb2025.smedico.entity.Medico;

public interface IMedicoService {
    List<Medico> findAll();
    Medico findById(Long id);
    void deleteById(Long id);

    Medico fromDto(MedicoRequestDTO dto) throws Exception;
    Medico create(Medico medico);
    Medico update(Long id, Medico medico) throws Exception;
}
