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
        // Delega la llamada al Query Method del Repository (case-insensitive)
        return estadoTurnoRepository.findByNombreContainingIgnoreCase(filtro);
    }

    @Override
    public long countByNombre(String nombre) {
        // Delega la llamada al Query Method del Repository (case-insensitive)
        return estadoTurnoRepository.countByNombreIgnoreCase(nombre);
    }
    
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

    @Override
    public EstadoTurno update(Long id, EstadoTurno estadoTurno) {
        Optional<EstadoTurno> existente = estadoTurnoRepository.findById(id);
        if (existente.isPresent()) {
            EstadoTurno actualizado = existente.get();
            actualizado.setNombre(estadoTurno.getNombre());
            return estadoTurnoRepository.save(actualizado);
        }
        // Cambio aquí: Usamos ResourceNotFoundException en lugar de RuntimeException
        throw new ResourceNotFoundException("EstadoTurno con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        // Cambio aquí: Primero verificamos si existe y lanzamos la excepción si no lo hace
        if (!estadoTurnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("EstadoTurno con id " + id + " no existe y no puede ser eliminado");
        }
        estadoTurnoRepository.deleteById(id);
    }

    @Override
    public EstadoTurno fromDto(EstadoTurnoRequestDto dto) {
        EstadoTurno estadoTurno = new EstadoTurno();
        
        // Si el DTO tiene ID (para una actualización o referencia), lo establecemos
        if (dto.getId() != null) {
            estadoTurno.setId(dto.getId()); 
        }
        
        estadoTurno.setNombre(dto.getNombre());
        return estadoTurno;
    }
}
