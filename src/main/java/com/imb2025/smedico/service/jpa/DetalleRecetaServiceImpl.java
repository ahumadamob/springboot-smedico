package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.DetalleRecetaRequestDto;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.entity.Medicamento;
import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.repository.DetalleRecetaRepository;
import com.imb2025.smedico.repository.MedicamentoRepository;
import com.imb2025.smedico.repository.RecetaRepository;
import com.imb2025.smedico.service.IDetalleRecetaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleRecetaServiceImpl implements IDetalleRecetaService {

    private final DetalleRecetaRepository repository;
    private final RecetaRepository recetaRepository;
    private final MedicamentoRepository medicamentoRepository;

    @Autowired
    public DetalleRecetaServiceImpl(DetalleRecetaRepository repository, RecetaRepository recetaRepository, MedicamentoRepository medicamentoRepository) {
        this.repository = repository;
        this.recetaRepository = recetaRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public List<DetalleReceta> findAll() {
        return repository.findAll();
    }

    @Override
    public DetalleReceta findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public DetalleReceta create(DetalleReceta detalleReceta) {
        return repository.save(detalleReceta);
    }

    @Override
    public DetalleReceta update(Long id, DetalleReceta detalleReceta) {
        detalleReceta.setId(id);
        return repository.save(detalleReceta);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public DetalleReceta fromDto(DetalleRecetaRequestDto dto) {
        Receta receta = recetaRepository.findById(dto.getRecetaId())
            .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada con ID: " + dto.getRecetaId()));
        Medicamento medicamento = medicamentoRepository.findById(dto.getMedicamentoId())
            .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado con ID: " + dto.getMedicamentoId()));

        DetalleReceta detalle = new DetalleReceta();
        detalle.setReceta(receta);
        detalle.setMedicamento(medicamento);
        detalle.setDosis(dto.getDosis());
        detalle.setFrecuencia(dto.getFrecuencia());
        return detalle;
    }
}
