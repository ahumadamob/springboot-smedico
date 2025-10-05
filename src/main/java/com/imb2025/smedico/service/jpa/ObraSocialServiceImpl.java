package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.ObraSocialRequestDto;
import com.imb2025.smedico.dto.ObraSocialResponseDto;
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
    public List<ObraSocialResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto) // ✅ Se convierte la entidad a DTO de respuesta
                .collect(Collectors.toList());
    }

    @Override
    public ObraSocialResponseDto findById(Long id) {
        ObraSocial obraSocial = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entidad no encontrada con id " + id));
        return toDto(obraSocial); // ✅ Devuelve DTO
    }

    @Override
    public ObraSocialResponseDto create(ObraSocialRequestDto dto) {
        if (repository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe una obra social con ese nombre.");
            // ✅ Cambio: se elimina "throws Exception" → usamos excepción específica
        }
        ObraSocial nueva = fromDto(dto);
        ObraSocial guardada = repository.save(nueva);
        return toDto(guardada); // ✅ Devuelve DTO
    }

    @Override
    public ObraSocialResponseDto update(Long id, ObraSocialRequestDto dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No existe la obra social con ID: " + id);
        }
        ObraSocial entidad = fromDto(dto);
        entidad.setId(id); // ✅ El id se setea aquí, no en el controller
        ObraSocial actualizada = repository.save(entidad);
        return toDto(actualizada); // ✅ Devuelve DTO
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

    // ✅ Métodos auxiliares privados de conversión
    private ObraSocial fromDto(ObraSocialRequestDto dto) {
        return new ObraSocial(
            dto.getNombre(),
            dto.getTelefono(),
            dto.getDireccion(),
            dto.getCobertura()
        );
    }

    private ObraSocialResponseDto toDto(ObraSocial entity) {
        ObraSocialResponseDto dto = new ObraSocialResponseDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setTelefono(entity.getTelefono());
        dto.setDireccion(entity.getDireccion());
        dto.setCobertura(entity.getCobertura());
        return dto;
    }

}




