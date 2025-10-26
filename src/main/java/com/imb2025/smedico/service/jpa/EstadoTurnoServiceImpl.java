package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.mapper.EstadoTurnoMapper; // Importamos el Mapper

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.request.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.repository.EstadoTurnoRepository;
import com.imb2025.smedico.service.IEstadoTurnoService;

@Service
public class EstadoTurnoServiceImpl implements IEstadoTurnoService {

    private final EstadoTurnoRepository estadoTurnoRepository;
    private final EstadoTurnoMapper mapper; 

    // Constructor con inyección de dependencias
    public EstadoTurnoServiceImpl(EstadoTurnoRepository estadoTurnoRepository, EstadoTurnoMapper mapper) {
        this.estadoTurnoRepository = estadoTurnoRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EstadoTurno> findAll() {
        return estadoTurnoRepository.findAll();
    }
    
    // ------------------------------------------------------------------
    // TP07: Implementación de Filtros
    // ------------------------------------------------------------------

    @Override
    public List<EstadoTurno> findByNombreContaining(String filtro) {
        return estadoTurnoRepository.findByNombreContainingIgnoreCase(filtro);
    }

    @Override
    public long countByNombre(String nombre) {
        return estadoTurnoRepository.countByNombreIgnoreCase(nombre);
    }
    
    // ------------------------------------------------------------------
    // EJERCICIO 1: Implementación de Filtros Booleanos
    // ------------------------------------------------------------------

    @Override
    public List<EstadoTurno> findFinales() {
        // Llama al Query Method mágico: findByEsFinalTrue()
        return estadoTurnoRepository.findByEsFinalTrue();
    }

    @Override
    public List<EstadoTurno> findPendientes() {
        // Llama al Query Method mágico: findByEsFinalFalse()
        return estadoTurnoRepository.findByEsFinalFalse();
    }
    
    // ------------------------------------------------------------------
    // Métodos CRUD
    // ------------------------------------------------------------------

    @Override
    public EstadoTurno findById(Long id) {
        return estadoTurnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "EstadoTurno no encontrado con id " + id));
    }


    @Override
    public boolean existsById(Long id) {
        return estadoTurnoRepository.existsById(id);
    }

    // TP08: El controlador usa mapper.toEntity(dto) y luego llama a create(entidad)
    @Override
    public EstadoTurno create(EstadoTurno estadoTurno) {
        return estadoTurnoRepository.save(estadoTurno);
    }

    @Override
    public EstadoTurno update(Long id, EstadoTurno estadoTurno) {
        // 1. Verifica la existencia
        EstadoTurno existente = findById(id);
        
        // 2. Control de Concurrencia (Asigna la versión que el Controller envió desde el DTO)
        if (estadoTurno.getVersion() != null) {
            existente.setVersion(estadoTurno.getVersion());
        }
        
        // 3. Asigna los campos de negocio
        existente.setNombre(estadoTurno.getNombre());
        if (estadoTurno.getEsFinal() != null) {
            existente.setEsFinal(estadoTurno.getEsFinal());
        }
        
        // La Entidad hereda id, createdAt, updatedAt, version.
        return estadoTurnoRepository.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        // Utilizamos findById para delegar el chequeo de existencia y la excepción
        findById(id); 
        estadoTurnoRepository.deleteById(id);
    }

    // ELIMINADO: Se elimina fromDto ya que la responsabilidad de mapeo es del Mapper (TP08)
    // Se comenta ya que la interfaz ya no lo tiene (pasos anteriores)
    /*
    @Override
    public EstadoTurno fromDto(EstadoTurnoRequestDto dto) {
        // La implementación ha sido movida a EstadoTurnoMapper.toEntity
        // Si la interfaz todavía tuviera fromDto, aquí se lanzaría una UnsupportedOperationException
        return mapper.toEntity(dto);
    }
    */
}
