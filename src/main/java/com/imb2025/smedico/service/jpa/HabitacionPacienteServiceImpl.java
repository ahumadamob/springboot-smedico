package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.request.HabitacionPacienteRequestDTO;
import com.imb2025.smedico.entity.HabitacionPaciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.HabitacionPacienteRepository;
import com.imb2025.smedico.service.IHabitacionPacienteService;

@Service
public class HabitacionPacienteServiceImpl implements IHabitacionPacienteService {

    @Autowired
    private HabitacionPacienteRepository habitacionRepository;

    @Override
    public List<HabitacionPaciente> findAll() {
        return habitacionRepository.findAll();
    }

    @Override
    public HabitacionPaciente findById(Long id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habitación no encontrada con ID " + id));
    }

    @Override
    public HabitacionPaciente create(HabitacionPaciente habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public HabitacionPaciente update(Long id, HabitacionPacienteRequestDTO dto) {
        if (!habitacionRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "No se puede actualizar. La habitación con ID " + id + " no existe.");
        }

        HabitacionPaciente habitacion = findById(id);
        mapearDTO(habitacion, dto);
        habitacion.setId(id);

        return habitacionRepository.save(habitacion);
    }

    @Override
    public void deleteById(Long id) {
        if (!habitacionRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "No se puede eliminar. La habitación con ID " + id + " no existe.");
        }
        habitacionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return habitacionRepository.existsById(id);
    }

    @Override
    public List<HabitacionPaciente> findBySectorIgnoreCase(String sector) {
        return habitacionRepository.findBySectorIgnoreCase(sector);
    }

    @Override
    public Long countBySectorIgnoreCase(String sector) {
        return habitacionRepository.countBySectorIgnoreCase(sector);
    }
    

    // Método helper para mapear DTO - entidad
    private void mapearDTO(HabitacionPaciente h, HabitacionPacienteRequestDTO dto) {
        h.setNumeroHabitacion(dto.getNumeroHabitacion());
        h.setPiso(dto.getPiso());
        h.setSector(dto.getSector());
        h.setCamasDisponibles(dto.getCamasDisponibles());
        h.setDescripcion(dto.getDescripcion());
    }

	@Override
	public HabitacionPaciente save(HabitacionPaciente entity) {
		return habitacionRepository.save(entity);
	}
}

