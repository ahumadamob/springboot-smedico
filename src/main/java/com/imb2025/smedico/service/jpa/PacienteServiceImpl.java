package com.imb2025.smedico.service.jpa;


import com.imb2025.smedico.dto.PacienteRequestDTO;


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


    // Guardar nuevo paciente con manejo de excepciones
    @Override
    public Paciente save(Paciente paciente) {
        try {
            return pacienteRepository.save(paciente);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el paciente: " + e.getMessage());
        }
    }

    // Actualizar paciente si existe
    public Paciente update(Long id, Paciente paciente) throws Exception {
        if (pacienteRepository.existsById(id)) {
            paciente.setId(id); // aseguramos que el ID no se pierda
            return pacienteRepository.save(paciente);
        } else {
            throw new Exception("Paciente no encontrado con id: " + id);
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
    public Paciente update(Long id, Paciente paciente) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Paciente no encontrado con id: " + id);
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


    // Convertir un DTO a una entidad Paciente

    public Paciente fromDto(PacienteRequestDTO requestDTO) {
        Paciente paciente = new Paciente();
        paciente.setNombre(requestDTO.getNombre());
        paciente.setApellido(requestDTO.getApellido());
        paciente.setDni(requestDTO.getDni());
        paciente.setEmail(requestDTO.getEmail());
        paciente.setTelefono(requestDTO.getTelefono());
        paciente.setFechaNacimiento(requestDTO.getFechaNacimiento());
        return paciente;
    }

}


	@Override
	public Paciente create(Paciente paciente) {
		// TODO Auto-generated method stub
		return null;
	}
}

}


