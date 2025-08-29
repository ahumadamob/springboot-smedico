package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.HorarioAtencionRequestDto;
import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.HorarioAtencionRepository;
import com.imb2025.smedico.service.IHorarioAtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioAtencionServiceImpl implements IHorarioAtencionService {

    @Autowired
    private HorarioAtencionRepository repository;

    @Override
    public List<HorarioAtencion> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los horarios: " + e.getMessage());
        }
    }

    @Override
    public HorarioAtencion findById(Long id) {
    	return repository.findById(id)
    		    .orElseThrow(() -> new ResourceNotFoundException(
    		        "Entidad no encontrada con id " + id));
    }

    @Override
    public HorarioAtencion create(HorarioAtencion horarioAtencion) throws Exception {
        try {
            return repository.save(horarioAtencion);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el horario: " + e.getMessage());
        }
    }

    @Override
    public HorarioAtencion update(Long id, HorarioAtencion horarioAtencion) throws Exception {
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("No existe un horario con ID: " + id);
            }
            horarioAtencion.setId(id);
            return repository.save(horarioAtencion);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el horario: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("No existe un horario con ID: " + id);
            }
            repository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el horario: " + e.getMessage());
        }
    }

    @Override
    public HorarioAtencion fromDto(HorarioAtencionRequestDto dto) throws Exception {
        HorarioAtencion horario = new HorarioAtencion();
        Medico medico = new Medico();
        medico.setId(dto.getMedicoId());
        horario.setMedico(medico);
        horario.setDiaSemana(dto.getDiaSemana());
        horario.setHoraInicio(dto.getHoraInicio());
        horario.setHoraFin(dto.getHoraFin());
        return horario;
    }

    @Override
    public boolean existsById(Long id) {
        try {
            return repository.existsById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar existencia: " + e.getMessage());
        }
    }
}
