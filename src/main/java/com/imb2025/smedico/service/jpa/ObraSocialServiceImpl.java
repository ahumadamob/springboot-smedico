package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.request.ObraSocialRequestDto;
import com.imb2025.smedico.dto.response.ObraSocialResponseDto;
import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.exception.ResourceNotFoundException; // <--- ¡ESTE ERA EL IMPORT FALTANTE!
import com.imb2025.smedico.mapper.ObraSocialMapper;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.service.IObraSocialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObraSocialServiceImpl implements IObraSocialService {

    @Autowired
    private ObraSocialRepository repository;

    @Autowired
    private ObraSocialMapper mapper;

    @Override
    public List<ObraSocialResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ObraSocialResponseDto create(ObraSocialRequestDto obraSocialDto) {
        ObraSocial entity = mapper.fromDto(obraSocialDto);
        ObraSocial saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public ObraSocialResponseDto update(Long id, ObraSocialRequestDto dto) {
                ObraSocial existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar: Obra Social no encontrada con id " + id));

        existente.setNombre(dto.getNombre());
        existente.setTelefono(dto.getTelefono());
        existente.setDireccion(dto.getDireccion());
        existente.setCobertura(dto.getCobertura());

        ObraSocial updated = repository.save(existente);
        return mapper.toResponseDto(updated);
    }

    @Override
    public ObraSocialResponseDto findById(Long id) {
        ObraSocial entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Obra Social no encontrada con id " + id));

        return mapper.toResponseDto(entity); 
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public long countByCobertura(String cobertura) {
        return repository.countByCobertura(cobertura);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: Obra Social no encontrada con id " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public ObraSocialResponseDto findByNombre(String nombre) {
        ObraSocial entity = repository.findByNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Obra social no encontrada con nombre: " + nombre));
        return mapper.toResponseDto(entity);
    }
}






