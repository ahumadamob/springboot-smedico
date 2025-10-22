package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.request.ObraSocialRequestDto;
import com.imb2025.smedico.dto.response.ObraSocialResponseDto;
import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.service.IObraSocialService;
import com.imb2025.smedico.mapper.ObraSocialMapper; // ✅ IMPORT CORRECTO

/**
 * Implementación de IObraSocialService utilizando JPA.
 */
@Service
public class ObraSocialServiceImpl implements IObraSocialService {

    @Autowired
    private ObraSocialRepository repository;

    @Override
    public List<ObraSocialResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(ObraSocialMapper::toResponseDto) // ✅ Mapper corregido
                .collect(Collectors.toList());
    }

    @Override
    public ObraSocialResponseDto findById(Long id) {
        ObraSocial obraSocial = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entidad no encontrada con id " + id));
        return ObraSocialMapper.toResponseDto(obraSocial);
    }

    @Override
    public ObraSocialResponseDto create(ObraSocialRequestDto dto) {
        if (repository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe una obra social con ese nombre.");
        }
        ObraSocial nueva = ObraSocialMapper.fromDto(dto); // ✅ Mapper corregido
        ObraSocial guardada = repository.save(nueva);
        return ObraSocialMapper.toResponseDto(guardada);
    }

    @Override
    public ObraSocialResponseDto update(Long id, ObraSocialRequestDto dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No existe la obra social con ID: " + id);
        }
        ObraSocial entidad = ObraSocialMapper.fromDto(dto); // ✅ Mapper corregido
        entidad.setId(id);
        ObraSocial actualizada = repository.save(entidad);
        return ObraSocialMapper.toResponseDto(actualizada);
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
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}






