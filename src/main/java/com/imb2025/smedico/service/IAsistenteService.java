package com.imb2025.smedico.service;
//Service

import java.util.List;
import com.imb2025.smedico.dto.AsistenteRequestDTO;
import com.imb2025.smedico.entity.Asistente;

public interface IAsistenteService {
    List<Asistente> findAll();
    Asistente findById(Long id);
    boolean existsById(Long id);
    Asistente create(Asistente asistente);
    Asistente update(Long id, Asistente asistente);
    void deleteById(Long id);
    Asistente fromDto(AsistenteRequestDTO dto);
}
