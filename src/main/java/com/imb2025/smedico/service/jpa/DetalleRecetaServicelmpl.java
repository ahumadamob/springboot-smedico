package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.DetalleRecetaRequestDTO;
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
public class DetalleRecetaServicelmpl implements IDetalleRecetaService {

    private final DetalleRecetaRepository repository;
    private final RecetaRepository recetaRepository;
    private final MedicamentoRepository medicamentoRepository;

    @Autowired
    public DetalleRecetaServicelmpl(DetalleRecetaRepository repository, RecetaRepository recetaRepository, MedicamentoRepository medicamentoRepository) {
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
    public DetalleReceta save(DetalleReceta detalleReceta) {
        return repository.save(detalleReceta);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public DetalleReceta saveFromDTO(DetalleRecetaRequestDTO dto) {
        // Validar y obtener entidades relacionadas
        Receta receta = recetaRepository.findById(dto.getRecetaId())
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada con ID: " + dto.getRecetaId()));

        Medicamento medicamento = medicamentoRepository.findById(dto.getMedicamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado con ID: " + dto.getMedicamentoId()));

        // Crear el nuevo detalle y setear valores
        DetalleReceta detalle = new DetalleReceta();
        detalle.setReceta(receta);
        detalle.setMedicamento(medicamento);
        detalle.setDosis(dto.getDosis());
        detalle.setFrecuencia(dto.getFrecuencia());

        return repository.save(detalle);
    }

    @Override
    public DetalleReceta updateFromDTO(Long id, DetalleRecetaRequestDTO dto) {
        DetalleReceta existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DetalleReceta no encontrado con ID: " + id));

        // Validar y obtener entidades relacionadas para actualizar
        Receta receta = recetaRepository.findById(dto.getRecetaId())
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada con ID: " + dto.getRecetaId()));

        Medicamento medicamento = medicamentoRepository.findById(dto.getMedicamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado con ID: " + dto.getMedicamentoId()));

        // Actualizar campos
        existente.setReceta(receta);
        existente.setMedicamento(medicamento);
        existente.setDosis(dto.getDosis());
        existente.setFrecuencia(dto.getFrecuencia());

        return repository.save(existente);
    }
}
