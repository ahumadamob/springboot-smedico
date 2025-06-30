package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.PacienteRequestDTO;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return pacienteRepository.existsById(id);
    }

    @Override
    public Paciente save(Paciente paciente) {
        try {
            return pacienteRepository.save(paciente);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el paciente: " + e.getMessage());
        }
    }

    @Override
    public Paciente create(Paciente paciente) {
        return save(paciente);
    }

    @Override
    public Paciente updatePaciente(Paciente paciente) {
        if (!existsById(paciente.getId())) {
            throw new IllegalArgumentException("Paciente no encontrado con id: " + paciente.getId());
        }
        return pacienteRepository.save(paciente);
    }

    @Override
    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar: Paciente no encontrado con ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente fromDto(PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setDni(dto.getDni());
        paciente.setEmail(dto.getEmail());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setTelefono(dto.getTelefono());
        return paciente;
    }
}