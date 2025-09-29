package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.dto.HistorialPacienteRequestDto;
import com.imb2025.smedico.entity.HistorialPaciente;
import com.imb2025.smedico.entity.Paciente;
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
            .orElseThrow(() -> new ResourceNotFoundException(
                "HistorialPaciente no encontrado con id " + id));
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
        System.out.println("Guardando historial: " + historial);
        return repo.save(historial);
    }

    @Override
    public HistorialPaciente update(Long id, HistorialPaciente historial) {
        HistorialPaciente existente = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No existe el historial con ID " + id));

        existente.setEvento(historial.getEvento());
        existente.setFecha(historial.getFecha());
        existente.setObservacion(historial.getObservacion());
        existente.setPaciente(historial.getPaciente());

        return repo.save(existente);
    }

    @Override
    public HistorialPaciente fromDto(HistorialPacienteRequestDto dto) {
        Paciente paciente = repoPaciente.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado " + dto.getPacienteId()));
        HistorialPaciente historial = new HistorialPaciente();

        historial.setEvento(dto.getEvento());
        historial.setFecha(dto.getFecha());
        historial.setObservacion(dto.getObservacion());
        historial.setPaciente(paciente);
        return historial;
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }
}

