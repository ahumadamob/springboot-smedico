package com.imb2025.smedico.service.jpa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.entity.HistorialPaciente;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.HistorialPacienteRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IHistorialPacienteService;

@Service
public class HistorialPacienteServiceImpl implements IHistorialPacienteService {

    @Autowired
    private HistorialPacienteRepository repo;

    @Autowired
    private PacienteRepository repoPaciente;

    @Override
    public List<HistorialPaciente> findAll() {
        return repo.findAll();
    }

    @Override
    public HistorialPaciente findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HistorialPaciente no encontrado con id " + id));
    }

    @Override
    public List<HistorialPaciente> findByEvento(String evento) {
        return repo.findByEvento(evento);
    }

    @Override
    public Long countByFecha(java.time.LocalDate fecha) {
        return repo.countByFecha(fecha);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("No existe el historial con ID " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public HistorialPaciente create(HistorialPaciente historial) {
        Long pacienteId = historial.getPaciente() != null ? historial.getPaciente().getId() : null;
        if (pacienteId == null) {
            throw new ResourceNotFoundException("Debe indicar un paciente válido");
        }

        Paciente paciente = repoPaciente.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado " + pacienteId));

        historial.setPaciente(paciente);
        return repo.save(historial);
    }

    @Override
    public HistorialPaciente update(Long id, HistorialPaciente historialActualizado) {
        HistorialPaciente existente = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el historial con ID " + id));

        existente.setEvento(historialActualizado.getEvento());
        existente.setFecha(historialActualizado.getFecha());
        existente.setObservacion(historialActualizado.getObservacion());
        existente.setFechaVigencia(historialActualizado.getFechaVigencia());

        Paciente paciente = repoPaciente.findById(historialActualizado.getPaciente().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Paciente no encontrado " + historialActualizado.getPaciente().getId()));
        existente.setPaciente(paciente);

        return repo.save(existente);
    }
    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }
    @Override
    public List<HistorialPaciente> findVigentes(LocalDate fechaHoy) {
        return repo.findByFechaVigenciaGreaterThanEqual(fechaHoy);
    }

    @Override
    public List<HistorialPaciente> findVencidos(LocalDate fechaHoy) {
        return repo.findByFechaVigenciaLessThan(fechaHoy);
    }

}
