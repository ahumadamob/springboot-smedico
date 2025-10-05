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
            // Solo actualizamos el nombre
            actualizado.setNombre(estadoTurno.getNombre());
            
            // Persiste los cambios
            return estadoTurnoRepository.save(actualizado);
        }
        
        // **MEJORA:** Usamos ResourceNotFoundException, no RuntimeException genérica
        throw new ResourceNotFoundException("EstadoTurno con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        estadoTurnoRepository.deleteById(id);
    }

    /**
     * Mapea un DTO a Entidad. Importante: maneja el ID si viene en el DTO (usado en el Controller para PUT).
     */
    @Override
    public EstadoTurno fromDto(EstadoTurnoRequestDto dto) {
        EstadoTurno estadoTurno = new EstadoTurno();
        // Permite que el ID del DTO se use si está presente (es importante para el update)
        estadoTurno.setId(dto.getId()); 
        estadoTurno.setNombre(dto.getNombre());
        return estadoTurno;
    }
    
    // ------------------------------------------------------------------
    // Implementación TP07: Filtros y Conteo
    // ------------------------------------------------------------------

    @Override
    public List<EstadoTurno> findByNombreContaining(String nombreFiltro) {
        // Llama al método mágico del Repository: findByNombreContainingIgnoreCase
        return estadoTurnoRepository.findByNombreContainingIgnoreCase(nombreFiltro);
    }
    
    @Override
    public long countByNombre(String nombre) {
        // Llama al método mágico del Repository: countByNombreIgnoreCase
        return estadoTurnoRepository.countByNombreIgnoreCase(nombre);
    }
}