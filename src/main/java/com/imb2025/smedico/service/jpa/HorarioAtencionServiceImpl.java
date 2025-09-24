package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.HorarioAtencionRequestDto;
import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.HorarioAtencionRepository;
import com.imb2025.smedico.service.IHorarioAtencionService;
import com.imb2025.smedico.service.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioAtencionServiceImpl implements IHorarioAtencionService {

    @Autowired
    private HorarioAtencionRepository repository;
    
    @Autowired
    private IMedicoService medicoService;

    @Override
    public List<HorarioAtencion> findAll() {
        return repository.findAll();
    }

    @Override
    public HorarioAtencion findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Entidad no encontrada con id " + id));
    }

    @Override
    public HorarioAtencion create(HorarioAtencionRequestDto requestDto) {
        HorarioAtencion horarioAtencion = fromDto(requestDto);
        return repository.save(horarioAtencion);
    }

    @Override
    public HorarioAtencion update(Long id, HorarioAtencionRequestDto requestDto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No existe un horario con ID: " + id);
        }
        HorarioAtencion horarioAtencion = fromDto(requestDto);
        horarioAtencion.setId(id);
        return repository.save(horarioAtencion);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No existe un horario con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public HorarioAtencion fromDto(HorarioAtencionRequestDto requestDto) {
        // Validar que el médico existe antes de crear la entidad
        Medico medico = medicoService.findById(requestDto.getMedicoId());
        
        HorarioAtencion horarioAtencion = new HorarioAtencion();
        horarioAtencion.setMedico(medico);
        horarioAtencion.setDiaSemana(requestDto.getDiaSemana());
        horarioAtencion.setHoraInicio(requestDto.getHoraInicio());
        horarioAtencion.setHoraFin(requestDto.getHoraFin());
        
        return horarioAtencion;
    }
}