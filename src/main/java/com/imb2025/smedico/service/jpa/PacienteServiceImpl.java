package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.PacienteRequestDto;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
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
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id " + id));
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
        Paciente existente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id " + id));
        // actualizar campos
        existente.setNombre(paciente.getNombre());
        existente.setApellido(paciente.getApellido());
        existente.setDni(paciente.getDni());
        existente.setEmail(paciente.getEmail());
        existente.setFechaNacimiento(paciente.getFechaNacimiento());
        existente.setTelefono(paciente.getTelefono());
        return pacienteRepository.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        Paciente existente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id " + id));
        pacienteRepository.delete(existente);
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

	@Override
	public List<Paciente> findAllOrder() {
		return pacienteRepository.findByOrderByApellidoAscNombreAsc();
	}

	@Override
	public List<Paciente> findByDni(String dni) {
		return pacienteRepository.findByDni(dni);
	}

	@Override
	public List<Paciente> findByDomainEmail(String domain) {
		return pacienteRepository.findByEmailEndingWith(domain);
	}

	@Override
	public Long countBy() {
		return pacienteRepository.countBy();
	}
}
