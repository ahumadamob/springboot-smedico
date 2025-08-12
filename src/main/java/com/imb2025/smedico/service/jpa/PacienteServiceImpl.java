package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.PacienteRequestDto;
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
    public Paciente create(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente update(Long id, Paciente paciente) {
        if (!existsById(paciente.getId())) {
            throw new IllegalArgumentException("Paciente no encontrado con id: " + paciente.getId());
        }
        paciente.setId(id);
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
    public Paciente fromDto(PacienteRequestDto dto) {
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
