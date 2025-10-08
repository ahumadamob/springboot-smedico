package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.imb2025.smedico.dto.AfiliacionRequestDto;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.AfiliacionRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.service.IAfiliacionService;

@Service
public class AfiliacionServiceImpl implements IAfiliacionService {

    @Autowired
    private AfiliacionRepository afiliacionRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ObraSocialRepository obraSocialRepository;

    @Override
    public List<Afiliacion> findAll() {
        return afiliacionRepository.findAll();
    }

    @Override
    public Afiliacion findById(Long id) {
        return afiliacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Afiliación no encontrada con id " + id));
    }

    @Override
    public Afiliacion create(Afiliacion afiliacion) {
        validarFechas(afiliacion);
        return afiliacionRepository.save(afiliacion);
    }

    @Override
    public Afiliacion update(Long id, Afiliacion afiliacion) {
        Afiliacion existente = findById(id); // valida existencia
        validarFechas(afiliacion);

        existente.setNumeroAfiliado(afiliacion.getNumeroAfiliado());
        existente.setFechaVigenciaDesde(afiliacion.getFechaVigenciaDesde());
        existente.setFechaHasta(afiliacion.getFechaHasta());
        existente.setPaciente(afiliacion.getPaciente());
        existente.setObra(afiliacion.getObra());

        return afiliacionRepository.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        Afiliacion existente = findById(id); // valida existencia
        afiliacionRepository.delete(existente);
    }

    @Override
    public Afiliacion fromDto(AfiliacionRequestDto dto) {
        // Validación de coherencia de fechas
        if (dto.getFechaHasta() != null && dto.getFechaVigenciaDesde() != null &&
            dto.getFechaHasta().isBefore(dto.getFechaVigenciaDesde())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "La fecha de vigencia hasta no puede ser anterior a la fecha desde");
        }

        Afiliacion afiliacion = new Afiliacion();
        afiliacion.setNumeroAfiliado(dto.getNumeroAfiliado());
        afiliacion.setFechaVigenciaDesde(dto.getFechaVigenciaDesde());
        afiliacion.setFechaHasta(dto.getFechaHasta());

        // Buscar Paciente por repositorio
        Paciente paciente = pacienteRepository.findById(dto.getIdpaciente())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Paciente no encontrado con id " + dto.getIdpaciente()));
        afiliacion.setPaciente(paciente);

        // Buscar ObraSocial por repositorio
        ObraSocial obra = obraSocialRepository.findById(dto.getIdobra())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Obra social no encontrada con id " + dto.getIdobra()));
        afiliacion.setObra(obra);

        return afiliacion;
    }

    @Override
    public boolean existsById(Long id) {
        return afiliacionRepository.existsById(id);
    }

    // ---- método privado para validar coherencia de fechas ----
    private void validarFechas(Afiliacion afiliacion) {
        if (afiliacion.getFechaHasta() != null &&
            afiliacion.getFechaHasta().isBefore(afiliacion.getFechaVigenciaDesde())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "El rango de fechas es inválido");
        }
    }
}
