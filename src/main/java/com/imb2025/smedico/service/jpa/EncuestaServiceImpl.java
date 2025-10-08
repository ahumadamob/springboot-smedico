package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException; // asegurate de tener esta clase
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.repository.EncuestaRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IEncuestaService;

@Service
@Transactional(readOnly = true)
public class EncuestaServiceImpl implements IEncuestaService {

    @Autowired
    private EncuestaRepository repo;
    @Autowired
    private PacienteRepository pacienteRepo;
    @Autowired
    private ConsultaRepository consultaRepo;

    @Override
    public List<Encuesta> findAll() {
        // Orden estable para evidencias en Postman/Swagger
        return repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Encuesta findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta no encontrada"));
    }

    @Transactional
    public Encuesta create(Encuesta encuesta) {
        // Si por error llega sin relaciones (no debería por @Valid), avisamos con 404
        if (encuesta.getPaciente() == null) {
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
        if (encuesta.getConsulta() == null) {
            throw new ResourceNotFoundException("Consulta no encontrada");
        }
        return repo.save(encuesta);
    }

    @Override
    @Transactional
    public Encuesta update(Long id, Encuesta encuesta) /* throws Exception */ {
        Encuesta actual = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta no encontrada"));

        if (encuesta.getPaciente() != null) {
            actual.setPaciente(encuesta.getPaciente());
        }
        if (encuesta.getConsulta() != null) {
            actual.setConsulta(encuesta.getConsulta());
        }
        // Puntaje/comentario vienen del DTO con @Valid
        actual.setPuntaje(encuesta.getPuntaje());
        actual.setComentario(encuesta.getComentario());

        return repo.save(actual);
    }

    @Override
    public Encuesta fromDto(EncuestaRequestDto dto) /* throws Exception */ {
        Paciente paciente = pacienteRepo.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        Consulta consulta = consultaRepo.findById(dto.getConsultaId())
                .orElseThrow(() -> new ResourceNotFoundException("Consulta no encontrada"));

        Encuesta e = new Encuesta();
        e.setPaciente(paciente);
        e.setConsulta(consulta);
        // dto.getPuntaje() es Integer con @NotNull: autounboxing seguro a int
        e.setPuntaje(dto.getPuntaje());
        e.setComentario(dto.getComentario());
        return e;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Encuesta no encontrada");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }
}
