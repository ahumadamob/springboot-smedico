package com.imb2025.smedico.service.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imb2025.smedico.dto.mapper.ConsultaMapper;
import com.imb2025.smedico.dto.request.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.repository.TurnoRepository;
import com.imb2025.smedico.service.IConsultaService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsultaServiceImpl implements IConsultaService {

    private final ConsultaRepository consultaRepository;
    private final TurnoRepository turnoRepository;

    public ConsultaServiceImpl(ConsultaRepository consultaRepository, TurnoRepository turnoRepository) {
        this.consultaRepository = consultaRepository;
        this.turnoRepository = turnoRepository;
    }

    @Override
    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    @Override
    public Consulta findById(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta no encontrada con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return consultaRepository.existsById(id);
    }

    // ============================================================
    // ✅ createFromDto usando el Mapper
    // ============================================================
    @Override
    @Transactional
    public Consulta createFromDto(ConsultaRequestDto dto) {
        // 1️⃣ Validar Turno existente
        Turno turno = turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con id " + dto.getTurnoId()));

        // 2️⃣ Validar unicidad (un Turno = una Consulta)
        if (consultaRepository.existsByTurno_Id(dto.getTurnoId())) {
            throw new IllegalArgumentException("El turno ya está asignado a otra consulta");
        }

        // 3️⃣ Crear la entidad desde el mapper
        Consulta nueva = ConsultaMapper.fromDto(dto, turno);

        // 4️⃣ Guardar
        return consultaRepository.save(nueva);
    }

    // ============================================================
    // ✅ updateFromDto usando el Mapper
    // ============================================================
    @Override
    @Transactional
    public Consulta updateFromDto(Long id, ConsultaRequestDto dto) {
        // 1️⃣ Verificar existencia de la consulta
        Consulta existente = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta no encontrada con id " + id));

        // 2️⃣ Validar Turno
        Turno turno = turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con id " + dto.getTurnoId()));

        // 3️⃣ Validar unicidad (el turno no puede estar en otra consulta)
        if (consultaRepository.existsByTurno_IdAndIdNot(dto.getTurnoId(), id)) {
            throw new IllegalArgumentException("El turno ya está asignado a otra consulta");
        }

        // 4️⃣ Actualizar la entidad existente usando el mapper
        ConsultaMapper.copyFromDto(dto, turno, existente);

        // 5️⃣ Guardar cambios
        return consultaRepository.save(existente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta no encontrada con id " + id);
        }
        consultaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Consulta> findByFechaBetween(LocalDate desde, LocalDate hasta, Pageable pageable) {
        if (desde == null || hasta == null)
            throw new IllegalArgumentException("Debe indicar las fechas 'desde' y 'hasta'");
        if (desde.isAfter(hasta))
            throw new IllegalArgumentException("'desde' no puede ser posterior a 'hasta'");
        return consultaRepository.findByFechaBetween(desde, hasta, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByPacienteId(Long pacienteId) {
        if (pacienteId == null)
            throw new IllegalArgumentException("Debe indicar el id del paciente");
        return consultaRepository.countByTurno_Paciente_Id(pacienteId);
    }
}
