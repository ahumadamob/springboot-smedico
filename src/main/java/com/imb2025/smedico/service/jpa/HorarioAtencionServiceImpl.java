package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.HorarioAtencion;
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
        return repository.findAll();
    }

    @Override
    public HorarioAtencion findById(Long id) {
    	return repository.findById(id)
    		    .orElseThrow(() -> new ResourceNotFoundException(
    		        "Entidad no encontrada con id " + id));
    }

    @Override
    public HorarioAtencion create(HorarioAtencion horarioAtencion) {
        return repository.save(horarioAtencion);
    }

    @Override
    public HorarioAtencion update(Long id, HorarioAtencion horarioAtencion) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No existe un horario con ID: " + id);
        }
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
}