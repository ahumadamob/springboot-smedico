package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.repository.EstadoTurnoRepository;
import com.imb2025.smedico.service.IEstadoTurnoService;

@Service
public class EstadoTurnoServiceImpl implements IEstadoTurnoService {

    private final EstadoTurnoRepository estadoTurnoRepository;

    // Constructor con inyección de dependencias
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

    @Override
    public EstadoTurno create(EstadoTurno estadoTurno) {
        // La entidad ya fue convertida de DTO en el Controlador
        return estadoTurnoRepository.save(estadoTurno);
    }

    @Override
    public EstadoTurno update(Long id, EstadoTurno estadoTurno) {
        // 1. Verifica la existencia
        EstadoTurno existente = findById(id);
        
        // 2. Control de Concurrencia (Asigna la versión y los campos que el Controller envió)
        if (estadoTurno.getVersion() != null) {
            // Usa setVersion, que espera Long (de BaseEntity)
            existente.setVersion(estadoTurno.getVersion()); 
        }
        
        // 3. Asigna los campos de negocio
        existente.setNombre(estadoTurno.getNombre());
        
        // 4. Se guarda (JPA verifica la versión)
        return estadoTurnoRepository.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        // Utilizamos findById para delegar el chequeo de existencia y la excepción
        findById(id); 
        estadoTurnoRepository.deleteById(id);
    }
    
    // ELIMINADO: Se elimina el método fromDto, ya que no existe en la interfaz.
}