package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.EstudioRequestDto;
import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.EspecialidadRepository;
import com.imb2025.smedico.repository.EstudioRepository;
import com.imb2025.smedico.repository.ResultadoEstudioRepository;
import com.imb2025.smedico.service.IEstudioService;

@Service
public class EstudioServiceImpl implements IEstudioService {

    @Autowired
    private EstudioRepository repoEstudio;

    @Autowired
    private EspecialidadRepository repoEspecialidad;

    @Autowired
    private ResultadoEstudioRepository repoResultadoEstudio;

    @Override
    public List<Estudio> findAll() {
        return repoEstudio.findAll();
    }

    @Override
    public Estudio findById(Long id) {
        return repoEstudio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudio no encontrado con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return repoEstudio.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Estudio no encontrado con id " + id);
        }
        repoEstudio.deleteById(id);
    }

    @Override
    public Estudio create(Estudio estudio) {
        // Validaciones mínimas SIN checked exceptions:
        if (estudio.getEspecialidad() == null) {
            // Si preferís, usá BadRequestException en lugar de IllegalArgumentException
            throw new IllegalArgumentException("Especialidad es obligatoria");
        }
        return repoEstudio.save(estudio);
    }

    @Override
    public Estudio update(Long id, Estudio estudio) {
        Estudio existente = repoEstudio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudio no encontrado con id " + id));

        existente.setNombre(estudio.getNombre());
        existente.setDescripcion(estudio.getDescripcion());
        existente.setEspecialidad(estudio.getEspecialidad());
        existente.setResultadoEstudio(estudio.getResultadoEstudio());

        return repoEstudio.save(existente);
    }

    @Override
    public Estudio fromDto(EstudioRequestDto dto) {
        Especialidad especialidad = repoEspecialidad.findById(dto.getEspecialidadId())
                .orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada: " + dto.getEspecialidadId()));

        ResultadoEstudio resultadoEstudio = null;
        if (dto.getResultadoEstudioId() != null) {
            resultadoEstudio = repoResultadoEstudio.findById(dto.getResultadoEstudioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Resultado de estudio no encontrado: " + dto.getResultadoEstudioId()));
        }

        Estudio estudio = new Estudio();
        estudio.setNombre(dto.getNombre());
        estudio.setDescripcion(dto.getDescripcion());
        estudio.setEspecialidad(especialidad);
        estudio.setResultadoEstudio(resultadoEstudio);

        return estudio;
    }

    @Override public List<Estudio> findAllOrder() {
        return repoEstudio.findAllByOrderByNombreAsc();
    }

    @Override public List<Estudio> findByNombre(String q) {
        return repoEstudio.findByNombreContainingIgnoreCase(q);
    }

    @Override public long countByEspecialidadId(Long especialidadId) {
        return repoEstudio.countByEspecialidad_Id(especialidadId);
    }
}
