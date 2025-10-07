package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.MedicoRequestDto;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.repository.EspecialidadRepository;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.service.IMedicoService;
import com.imb2025.smedico.exception.ResourceNotFoundException;

@Service
public class MedicoServiceImpl implements IMedicoService {

    @Autowired
    private MedicoRepository repo;

    @Autowired
    private EspecialidadRepository repoEspecialidad;

    @Override
    public List<Medico> findAll() {
        return repo.findAll();
    }

    @Override
    public Medico findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Medico no encontrado con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("El Medico con ID " + id + " no existe");
        }
        repo.deleteById(id);
    }

    @Override
    public Medico create(Medico medico) {
        return repo.save(medico);
    }

    @Override
    public Medico update(Long id, Medico medico) throws Exception {
        if (repo.existsById(id)) {
            medico.setId(id);
            return repo.save(medico);
        } else {
            throw new Exception("Medico con ID " + id + " no existe");
        }
    }
    
    @Override
    public List<Medico> findByApellido(String apellido) {
        return repo.findByApellido(apellido);
    }

    @Override
    public Long countByEspecialidad(String nombreEspecialidad) {
        return repo.countByEspecialidad_Nombre(nombreEspecialidad);
    }

    @Override
    public Medico fromDto(MedicoRequestDto dto) throws Exception {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar nulo o vacío");
        }
        if (dto.getApellido() == null || dto.getApellido().isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar nulo o vacío");
        }
        if (dto.getMatricula() == null || dto.getMatricula().isBlank()) {
            throw new IllegalArgumentException("La matrícula no puede estar nula o vacía");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("El email no puede estar nulo o vacío");
        }
        if (dto.getTelefono() == null || dto.getTelefono().isBlank()) {
            throw new IllegalArgumentException("El teléfono no puede estar nulo o vacío");
        }

        Especialidad especialidad = repoEspecialidad.findById(dto.getEspecialidadId())
                .orElseThrow(() -> new Exception("No se encontró especialidad con el id: " + dto.getEspecialidadId()));

        Medico medico = new Medico();
        medico.setNombre(dto.getNombre());
        medico.setApellido(dto.getApellido());
        medico.setMatricula(dto.getMatricula());
        medico.setEspecialidad(especialidad);
        medico.setEmail(dto.getEmail());
        medico.setTelefono(dto.getTelefono());

        return medico;
    }

  
}
