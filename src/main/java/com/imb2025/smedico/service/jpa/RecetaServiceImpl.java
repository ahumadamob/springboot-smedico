package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.request.RecetaRequestDto;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.repository.RecetaRepository;
import com.imb2025.smedico.service.IRecetaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetaServiceImpl implements IRecetaService {


    @Autowired
    private RecetaRepository repo;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Receta> findAll() {
        return repo.findAll();
    }

    @Override
    public Receta findById(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException(
                       "Entidad no encontrada con id " + id));
    }

    @Override
    public Receta create(Receta receta) {
        return repo.save(receta);
    }

    @Override
    public Receta update(Long id, Receta receta) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Receta con ID " + id + " no existe");
        }
        receta.setId(id);
        return repo.save(receta);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Receta con ID " + id + " no existe");
        }
        repo.deleteById(id);
    }


    @Override
    public List<Receta> findByFecha(LocalDate fecha) {
        return repo.findByFecha(fecha);
    }

    @Override
    public long countByFecha(LocalDate fecha) {
        return repo.countByFecha(fecha);
        }

    
}
