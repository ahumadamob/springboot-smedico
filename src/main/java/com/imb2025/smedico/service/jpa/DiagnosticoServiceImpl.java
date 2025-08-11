package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.DiagnosticoRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.repository.DiagnosticoRepository;
import com.imb2025.smedico.service.IDiagnosticoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DiagnosticoServiceImpl implements IDiagnosticoService {

    @Autowired
    private DiagnosticoRepository repo;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public List<Diagnostico> findAll() {
        return repo.findAll();
    }

    @Override
    public Diagnostico findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Diagnostico create(Diagnostico diagnostico) {
        return repo.save(diagnostico);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Diagnóstico con ID " + id + " no encontrado.");
        }
        repo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    public Diagnostico fromDto(DiagnosticoRequestDto dto) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setDescripcion(dto.getDescripcion());
        diagnostico.setFechaDiagnostico(dto.getFechaDiagnostico());

        Consulta consulta = consultaRepository.findById(dto.getConsultaId())
            .orElseThrow(() -> new RuntimeException("Consulta no encontrada"));

        diagnostico.setConsulta(consulta);

        return diagnostico;
    }


    @Override
    public Diagnostico update(Diagnostico diagnostico, Long id) {
        Diagnostico existente = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado"));

        existente.setDescripcion(diagnostico.getDescripcion());
        existente.setFechaDiagnostico(diagnostico.getFechaDiagnostico());
        existente.setConsulta(diagnostico.getConsulta());

        return repo.save(existente);
    }
}
