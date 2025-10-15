package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.EspecialidadRequestDto;
import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.exception.ResourceNotFoundException;

import java.util.List;

public interface IEspecialidadService {
    public List<Especialidad> findAll();
    public Especialidad create(Especialidad especialidad);
    public Especialidad update(Long id, Especialidad especialidad);
    public Especialidad findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Especialidad fromDto(EspecialidadRequestDto especialidadRequestDto);
    public List<Especialidad> findByNombre(String nombre);
    public long countByDescripcion(String descripcion);
}
