
package com.imb2025.smedico.service;
import java.util.List;
import com.imb2025.smedico.dto.request.HabitacionPacienteRequestDTO;
import com.imb2025.smedico.entity.HabitacionPaciente;





public interface IHabitacionPacienteService {

    List<HabitacionPaciente> findAll();

    HabitacionPaciente create(HabitacionPaciente habitacionPaciente);

    HabitacionPaciente update(Long id, HabitacionPacienteRequestDTO dto);

    HabitacionPaciente findById(Long id);

    List<HabitacionPaciente> findBySectorIgnoreCase(String sector);

    Long countBySectorIgnoreCase(String sector);

    boolean existsById(Long id);

    void deleteById(Long id);

    List<HabitacionPaciente> findConObservacion();

    List<HabitacionPaciente> findSinObservacion();

    HabitacionPaciente save(HabitacionPaciente entity);
}
