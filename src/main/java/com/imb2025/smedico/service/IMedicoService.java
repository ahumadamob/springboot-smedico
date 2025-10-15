package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.MedicoRequestDto;
import com.imb2025.smedico.entity.Medico;
import java.util.List;

public interface IMedicoService {
    public List<Medico> findAll();
    public Medico create(Medico medico);
    public Medico update(Long id, Medico medico) throws Exception;
    public Medico findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Medico fromDto(MedicoRequestDto medicoRequestDto) throws Exception;
    
    public List <Medico> findByApellido (String Apellido);
    public Long countByEspecialidad (String nombreEspecialidad);
}
