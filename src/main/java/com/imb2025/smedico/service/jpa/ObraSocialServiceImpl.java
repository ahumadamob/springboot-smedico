package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.ObraSocialRequestDto;
import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.service.IObraSocialService;

/**
 * Implementación de IObraSocialService utilizando JPA.
 */
@Service
public class ObraSocialServiceImpl implements IObraSocialService {

    @Autowired
    private ObraSocialRepository repository;

    @Override
    public List<ObraSocial> findAll() {
        return repository.findAll();
    }

    @Override
    public ObraSocial findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Entidad no encontrada con id " + id));
    }

    @Override
    public ObraSocial create(ObraSocial obraSocial) throws Exception {
        // Validación opcional si nombre es único en DB
        if (repository.existsByNombre(obraSocial.getNombre())) {
            throw new Exception("Ya existe una obra social con ese nombre.");
        }
        return repository.save(obraSocial);
    }

    @Override
    public ObraSocial update(Long id, ObraSocial obraSocial) throws Exception {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No existe la obra social con ID: " + id);
        }
        obraSocial.setId(id);
        return repository.save(obraSocial);
    }

    @Override
    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("No se encontró la obra social con ID: " + id);
        }
    }

    @Override
    public ObraSocial fromDto(ObraSocialRequestDto dto) throws Exception {
        // Aquí podrías agregar validaciones manuales si lo deseas
        return new ObraSocial(
            dto.getNombre(),
            dto.getTelefono(),
            dto.getDireccion(),
            dto.getCobertura()
        );
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}



