package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.mapper.EstadoTurnoMapper; // Nuevo import del Mapper

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
    private final EstadoTurnoMapper mapper; // Inyectamos el Mapper

    // Inyección de dependencias por constructor
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
            
            // Asignamos la nueva versión del nombre
            actualizado.setNombre(estadoTurno.getNombre());
            
            // Nota importante para TP08: El campo 'version' DEBE ser asignado 
            // a la entidad antes de guardar para el control de concurrencia.
            // Esto se hace en el Controller antes de llamar al update, si es necesario.
            
            return estadoTurnoRepository.save(actualizado);
        }
        // MEJORA: Usamos ResourceNotFoundException
        throw new ResourceNotFoundException("EstadoTurno con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        // Mejoramos la lógica de borrado con manejo de excepción
        if (!estadoTurnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("EstadoTurno con id " + id + " no existe y no puede ser eliminado");
        }
        estadoTurnoRepository.deleteById(id);
    }

    // ELIMINADO: Ya no existe en la interfaz
    // @Override
    // public EstadoTurno fromDto(EstadoTurnoRequestDto dto) { ... }
}
