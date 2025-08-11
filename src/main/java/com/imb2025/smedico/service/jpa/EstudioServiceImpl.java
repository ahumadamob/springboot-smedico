package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.EstudioRequestDto;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.repository.EspecialidadRepository;
import com.imb2025.smedico.repository.EstudioRepository;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.repository.OrdenEstudioRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.repository.ResultadoEstudioRepository;
import com.imb2025.smedico.service.IEstudioService;

@Service
public class EstudioServiceImpl implements IEstudioService {

    @Autowired
    private EstudioRepository repo;
    @Autowired
    private PacienteRepository repoPaciente;
    @Autowired
    private MedicoRepository repoMedico;
    @Autowired
    private EspecialidadRepository repoEspecialidad;
    @Autowired
    private ObraSocialRepository repoObraSocial;
    @Autowired
    private OrdenEstudioRepository repoOredenEstudio;
    @Autowired
    private ResultadoEstudioRepository repoResultadoEstudio;

    @Override
    public List<Estudio> findAll() {
        return repo.findAll();
    }

    @Override
    public Estudio findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public Estudio create(Estudio estudio) throws Exception {
        return repo.save(estudio);
    }

    @Override
    public Estudio update(Estudio estudio, Long id) throws Exception {
        if (!repo.existsById(id)) {
            throw new Exception("No existe el estudio con ID: " + id);
        }
        estudio.setId(id);
        return repo.save(estudio);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Estudio con ID " + id + " no existe.");
        }
        repo.deleteById(id);
    }

    @Override
    public Estudio fromDto(EstudioRequestDto dto) throws Exception {
        return new Estudio(
            dto.getNombre(),
            dto.getDescripcion(),
            repoPaciente.findById(dto.getPacientId()).orElseThrow(() -> new Exception("Paciente no encontrado")),
            repoMedico.findById(dto.getMedicoId()).orElseThrow(() -> new Exception("Médico no encontrado")),
            repoEspecialidad.findById(dto.getEspecialidadId()).orElseThrow(() -> new Exception("Especialidad no encontrada")),
            repoObraSocial.findById(dto.getObraSocialId()).orElseThrow(() -> new Exception("Obra social no encontrada")),
            repoOredenEstudio.findById(dto.getOredenEstudioId()).orElseThrow(() -> new Exception("Orden de estudio no encontrada")),
            repoResultadoEstudio.findById(dto.getResultadoEstudioId()).orElseThrow(() -> new Exception("Resultado de estudio no encontrado"))
        );
    }
}
