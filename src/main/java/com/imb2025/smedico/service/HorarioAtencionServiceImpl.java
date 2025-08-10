package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.exception.RecursoNoEncontradoException;
import com.imb2025.smedico.repository.HorarioAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioAtencionServiceImpl implements IHorarioAtencionService {

    @Autowired
    private HorarioAtencionRepository repository;

    @Override
    public List<HorarioAtencion> getAllHorarioAtencion() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los horarios: " + e.getMessage());
        }
    }

    @Override
    public HorarioAtencion getHorarioAtencionById(Long id) {
        try {
            return repository.findById(id)
                    .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el horario: " + e.getMessage());
        }
    }

    @Override
    public HorarioAtencion save(HorarioAtencion horarioAtencionEntity) {
        try {
            return repository.save(horarioAtencionEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el horario: " + e.getMessage());
        }
    }

    @Override
    public void deleteHorarioAtencion(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new RecursoNoEncontradoException("No existe un horario con ID: " + id);
            }
            repository.deleteById(id);
        } catch (RecursoNoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el horario: " + e.getMessage());
        }
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