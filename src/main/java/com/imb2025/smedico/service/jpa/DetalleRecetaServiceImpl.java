package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.dto.request.DetalleRecetaRequestDto;
import com.imb2025.smedico.dto.response.DetalleRecetaResponseDto;
import com.imb2025.smedico.dto.mapper.DetalleRecetaMapper;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.repository.DetalleRecetaRepository;
import com.imb2025.smedico.repository.MedicamentoRepository;
import com.imb2025.smedico.repository.RecetaRepository;
import com.imb2025.smedico.service.IDetalleRecetaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleRecetaServiceImpl implements IDetalleRecetaService {

    @Autowired
    private final DetalleRecetaRepository repository;

    @Autowired
    private final RecetaRepository recetaRepository;

    @Autowired
    private final MedicamentoRepository medicamentoRepository;

    public DetalleRecetaServiceImpl(DetalleRecetaRepository repository, RecetaRepository recetaRepository, MedicamentoRepository medicamentoRepository) {
        this.repository = repository;
        this.recetaRepository = recetaRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public List<DetalleRecetaResponseDto> findAll() {
        return repository.findAll().stream()
                .map(DetalleRecetaMapper::toResponseDto)
                .toList();
    }

    @Override
    public DetalleRecetaResponseDto findById(Long id) {
        DetalleReceta detalle = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleReceta no encontrada con id " + id));
        return DetalleRecetaMapper.toResponseDto(detalle);
    }

    @Override
    public DetalleRecetaResponseDto create(DetalleRecetaRequestDto dto) {
        // RequestDto → Entidad usando Mapper
        DetalleReceta detalle = DetalleRecetaMapper.fromDto(dto);

        // Validar y asociar Receta y Medicamento
        detalle.setReceta(recetaRepository.findById(dto.getRecetaId())
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada con ID: " + dto.getRecetaId())));
        detalle.setMedicamento(medicamentoRepository.findById(dto.getMedicamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado con ID: " + dto.getMedicamentoId())));

        DetalleReceta saved = repository.save(detalle);
        return DetalleRecetaMapper.toResponseDto(saved);
    }

    @Override
    public DetalleRecetaResponseDto update(Long id, DetalleRecetaRequestDto dto) {
        DetalleReceta detalle = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleReceta no encontrada con id " + id));

        detalle.setReceta(recetaRepository.findById(dto.getRecetaId())
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada con ID: " + dto.getRecetaId())));
        detalle.setMedicamento(medicamentoRepository.findById(dto.getMedicamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado con ID: " + dto.getMedicamentoId())));
        detalle.setDosis(dto.getDosis());
        detalle.setFrecuencia(dto.getFrecuencia());

        DetalleReceta updated = repository.save(detalle);
        return DetalleRecetaMapper.toResponseDto(updated);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("DetalleReceta no encontrada con id " + id);
        }
        repository.deleteById(id);
    }
}

