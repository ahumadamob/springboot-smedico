package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.repository.EstadoTurnoRepository;
import com.imb2025.smedico.service.IEstadoTurnoService;

@Service
public class EstadoTurnoServiceImpl implements IEstadoTurnoService {

    private final EstadoTurnoRepository estadoTurnoRepository;

    // Se elimina la inyección del Mapper
    public EstadoTurnoServiceImpl(EstadoTurnoRepository estadoTurnoRepository) {
        this.estadoTurnoRepository = estadoTurnoRepository;
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
    // EJERCICIO 1: Implementación de Filtros Booleanos (NUEVO)
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
    // Métodos CRUD (Restaurados sin lógica del Mapper)
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

    @Override
    public EstadoTurno create(EstadoTurno estadoTurno) {
        return estadoTurnoRepository.save(estadoTurno);
    }

    // Se restaura la lógica sin el Mapper y añadiendo los campos de TP08/Ejercicio 1
    @Override
    public EstadoTurno update(Long id, EstadoTurno estadoTurno) {
        EstadoTurno existente = findById(id);
        
        // El campo 'version' DEBE ser asignado a la entidad por el Controller
        if (estadoTurno.getVersion() != null) {
            existente.setVersion(estadoTurno.getVersion());
        }
        
        // Asigna los campos de negocio
        existente.setNombre(estadoTurno.getNombre());
        if (estadoTurno.getEsFinal() != null) {
            existente.setEsFinal(estadoTurno.getEsFinal());
        }
        
        return estadoTurnoRepository.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        findById(id); // Usa la función findById para el chequeo de existencia
        estadoTurnoRepository.deleteById(id);
    }

    // Se restaura el método fromDto (responsable del mapeo temporalmente)
    @Override
    public EstadoTurno fromDto(EstadoTurnoRequestDto dto) {
        EstadoTurno estadoTurno = new EstadoTurno();
        
        // Asignación de campos para la entrada (necesario para el Controller)
        estadoTurno.setId(dto.getId()); 
        estadoTurno.setNombre(dto.getNombre());
        if (dto.getEsFinal() != null) {
            estadoTurno.setEsFinal(dto.getEsFinal());
        }
        
        return estadoTurno;
    }
}