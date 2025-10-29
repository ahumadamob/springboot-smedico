package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.DiagnosticoRequestDto;
import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.DiagnosticoRepository;
import com.imb2025.smedico.service.IDiagnosticoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiagnosticoServiceImpl implements IDiagnosticoService {

    private final DiagnosticoRepository repo;

    public DiagnosticoServiceImpl(DiagnosticoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Diagnostico> findAll() {
        // No debería lanzar excepción; si la lista está vacía, el controller decide qué devolver
        return repo.findAll();
    }

    @Override
    public Diagnostico findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diagnóstico no encontrado con id " + id));
    }

    @Transactional
    @Override
    public Diagnostico create(Diagnostico diagnostico) {
        // save inserta si id == null
        return repo.save(diagnostico);
    }

    @Transactional
    @Override
    public Diagnostico update(Long id, Diagnostico diagnostico) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("No se puede actualizar. Diagnóstico no encontrado con id " + id);
        }
        diagnostico.setId(id);
        return repo.save(diagnostico);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Diagnóstico no encontrado con id " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public Diagnostico fromDto(DiagnosticoRequestDto dto) {
        Diagnostico d = new Diagnostico();
        d.setConsultaId(dto.getConsultaId());
        d.setDescripcion(dto.getDescripcion());
        d.setFechaDiagnostico(dto.getFechaDiagnostico());
        return d;
    }
}
