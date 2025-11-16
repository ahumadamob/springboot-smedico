package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Especialidad;
import java.util.List;

public interface IEspecialidadService {
    public List<Especialidad> findAll();
    public Especialidad create(Especialidad especialidad);
    public Especialidad update(Long id, Especialidad especialidad);
    public Especialidad findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public List<Especialidad> findByNombre(String nombre);
    public long countByDescripcion(String descripcion);
}
