package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.EstudioRequestDto;
import com.imb2025.smedico.entity.*;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.*;
import com.imb2025.smedico.service.IEstudioService;

@Service
public class EstudioServiceImpl implements IEstudioService {

    @Autowired private EstudioRepository repo;
    @Autowired private PacienteRepository repoPaciente;
    @Autowired private MedicoRepository repoMedico;
    @Autowired private EspecialidadRepository repoEspecialidad;
    @Autowired private ObraSocialRepository repoObraSocial;
    @Autowired private OrdenEstudioRepository repoOredenEstudio;
    @Autowired private ResultadoEstudioRepository repoResultadoEstudio;

    @Override
    public List<Estudio> findAll() {
        return repo.findAll();
    }

    @Override
    public Estudio findById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Estudio no encontrado con id " + id));
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
    public Estudio update(Long id, Estudio estudio) throws Exception {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("No existe el estudio con ID: " + id);
        }
        estudio.setId(id);
        return repo.save(estudio);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Estudio con ID " + id + " no existe.");
        }
        repo.deleteById(id);
    }

    @Override
    public Estudio fromDto(EstudioRequestDto dto) throws Exception {
        Especialidad especialidad = repoEspecialidad.findById(dto.getEspecialidadId())
            .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada"));
        Paciente paciente = repoPaciente.findById(dto.getPacientId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        Medico medico = repoMedico.findById(dto.getMedicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado"));
        ObraSocial obraSocial = repoObraSocial.findById(dto.getObraSocialId())
            .orElseThrow(() -> new ResourceNotFoundException("Obra social no encontrada"));

        OrdenEstudio ordenEstudio = null;
        if (dto.getOredenEstudioId() != null) {
            ordenEstudio = repoOredenEstudio.findById(dto.getOredenEstudioId())
                .orElseThrow(() -> new ResourceNotFoundException("Orden de estudio no encontrada"));
        }

        ResultadoEstudio resultadoEstudio = null;
        if (dto.getResultadoEstudioId() != null) {
            resultadoEstudio = repoResultadoEstudio.findById(dto.getResultadoEstudioId())
                .orElseThrow(() -> new ResourceNotFoundException("Resultado de estudio no encontrado"));
        }

        Estudio estudio = new Estudio();
        estudio.setNombre(dto.getNombre());            // (nuevo) mapeo de nombre
        estudio.setDescripcion(dto.getDescripcion());
        estudio.setEspecialidad(especialidad);
        estudio.setMedico(medico);
        estudio.setObraSocial(obraSocial);
        estudio.setOredenEstudio(ordenEstudio);
        estudio.setPaciente(paciente);
        estudio.setResultadoEstudio(resultadoEstudio);
        return estudio;
    }
}
