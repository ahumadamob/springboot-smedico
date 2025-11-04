package com.imb2025.smedico.service;

import java.util.List;
import com.imb2025.smedico.dto.request.HabitacionPacienteRequestDTO;
import com.imb2025.smedico.entity.HabitacionPaciente;

public interface IHabitacionPacienteService {

    // Obtener todas las habitaciones
    List<HabitacionPaciente> findAll();

    // Crear una nueva habitación
    HabitacionPaciente create(HabitacionPaciente habitacionPaciente);

    // Actualizar una habitación existente
    HabitacionPaciente update(Long id, HabitacionPacienteRequestDTO dto);
    
    
    // Buscar habitación por ID
    HabitacionPaciente findById(Long id);
    
    List<HabitacionPaciente> findBySectorIgnoreCase(String sector);
    Long countBySectorIgnoreCase(String sector);


    // Verificar existencia por ID
    boolean existsById(Long id);

    // Eliminar habitación por ID
    void deleteById(Long id);

	HabitacionPaciente save(HabitacionPaciente entity);
}
