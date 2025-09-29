package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.HabitacionPacienteRequestDTO;
import com.imb2025.smedico.entity.HabitacionPaciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.HabitacionPacienteRepository;
import com.imb2025.smedico.service.HabitacionPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionPacienteServiceImpl implements HabitacionPacienteService {

    @Autowired
    private HabitacionPacienteRepository habitacionPacienteRepository;

    @Override
    public List<HabitacionPaciente> findAll() {
        return habitacionPacienteRepository.findAll();
    }

    @Override
    public HabitacionPaciente findById(Long id) {
        return habitacionPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habitación no encontrada con ID: " + id));
    }

    @Override
    public HabitacionPaciente save(HabitacionPaciente habitacionPaciente) {
        return habitacionPacienteRepository.save(habitacionPaciente);
    }

    @Override
    public HabitacionPaciente update(Long id, HabitacionPacienteRequestDTO dto) {
        HabitacionPaciente habitacion = habitacionPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la habitación con ID: " + id));

        mapearDTO(habitacion, dto);
        return habitacionPacienteRepository.save(habitacion);
    }

    @Override
    public void deleteById(Long id) {
        if (!habitacionPacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se encontró la habitación con ID: " + id);
        }
        habitacionPacienteRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return habitacionPacienteRepository.existsById(id);
    }

    @Override
    public HabitacionPaciente fromDto(HabitacionPacienteRequestDTO dto) {
        HabitacionPaciente habitacion = new HabitacionPaciente();
        mapearDTO(habitacion, dto);
        return habitacion;
    }

    private void mapearDTO(HabitacionPaciente habitacion, HabitacionPacienteRequestDTO dto) {
        habitacion.setNumeroHabitacion(dto.getNumeroHabitacion());
        habitacion.setPiso(dto.getPiso());
        habitacion.setSector(dto.getSector());
        habitacion.setCamasDisponibles(dto.getCamasDisponibles());
        habitacion.setDescripcion(dto.getDescripcion());
    }
}
