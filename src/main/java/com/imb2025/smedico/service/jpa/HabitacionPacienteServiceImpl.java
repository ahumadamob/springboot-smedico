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
    public List<HabitacionPaciente> findConObservacion() {
        return habitacionRepository.findByObservacionInternaIsNotNull();
    }

    @Override
    public List<HabitacionPaciente> findSinObservacion() {
        return habitacionRepository.findByObservacionInternaIsNull();
    }

    // Normaliza el texto de la observación
    private String normalizarObservacion(String valor) {

        if (valor == null) {
            return null;
        }

        String texto = valor.trim().toUpperCase();

        if (texto.isBlank()) {
            return null;
        }

        if (contienePalabraProhibida(texto)) {
            throw new RuntimeException("La observación contiene palabras prohibidas");
        }

        return texto;
    }

    // Palabras que no se permiten
    private boolean contienePalabraProhibida(String texto) {
        return texto.contains("TEST") ||
               texto.contains("PRUEBA") ||
               texto.contains("XXX");
    }

    @Override
    public List<HabitacionPaciente> findAll() {
        return habitacionRepository.findAll();
    }

    @Override
    public HabitacionPaciente findById(Long id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habitación no encontrada con ID " + id));
    }

    @Override
    public HabitacionPaciente create(HabitacionPaciente habitacion) {

        habitacion.setObservacionInterna(
                normalizarObservacion(habitacion.getObservacionInterna())
        );

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

        habitacion.setObservacionInterna(
                normalizarObservacion(dto.getObservacionInterna())
        );

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

    // Método helper para mapear DTO a entidad
    private void mapearDTO(HabitacionPaciente h, HabitacionPacienteRequestDTO dto) {

        h.setNumeroHabitacion(dto.getNumeroHabitacion());
        h.setPiso(dto.getPiso());
        h.setSector(dto.getSector());
        h.setCamasDisponibles(dto.getCamasDisponibles());
        h.setDescripcion(dto.getDescripcion());
    }

    @Override
    public HabitacionPaciente save(HabitacionPaciente entity) {

        entity.setObservacionInterna(
                normalizarObservacion(entity.getObservacionInterna())
        );

        return habitacionRepository.save(entity);
    }
}
