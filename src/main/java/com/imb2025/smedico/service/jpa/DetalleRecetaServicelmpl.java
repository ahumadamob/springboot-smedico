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

    @Autowired
    private DetalleRecetaRepository repository;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<DetalleReceta> findAll() {
        return repository.findAll();
    }

    @Override
    public DetalleReceta findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public DetalleReceta save(DetalleReceta recetaMedicamento) {
        return repository.save(recetaMedicamento);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public DetalleReceta saveFromDTO(DetalleRecetaRequestDTO dto) {
        try {
            DetalleReceta detalle = new DetalleReceta();

            // Validar Receta
            Receta receta = recetaRepository.findById(dto.getRecetaId())
                    .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada con ID: " + dto.getRecetaId()));
            detalle.setReceta(receta);

            // Validar Medicamento
            Medicamento medicamento = medicamentoRepository.findById(dto.getMedicamentoId())
                    .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado con ID: " + dto.getMedicamentoId()));
            detalle.setMedicamento(medicamento);

            detalle.setDosis(dto.getDosis());
            detalle.setFrecuencia(dto.getFrecuencia());

            return repository.save(detalle);

        } catch (Exception e) {
            throw new RuntimeException("Error al guardar DetalleReceta: " + e.getMessage());
        }
    }

    @Override
    public DetalleReceta updateFromDTO(Long id, DetalleRecetaRequestDTO dto) {
        try {
            DetalleReceta existente = repository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("DetalleReceta no encontrado con ID: " + id));

            // Validar Receta
            Receta receta = recetaRepository.findById(dto.getRecetaId())
                    .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada con ID: " + dto.getRecetaId()));
            existente.setReceta(receta);

            // Validar Medicamento
            Medicamento medicamento = medicamentoRepository.findById(dto.getMedicamentoId())
                    .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado con ID: " + dto.getMedicamentoId()));
            existente.setMedicamento(medicamento);

            existente.setDosis(dto.getDosis());
            existente.setFrecuencia(dto.getFrecuencia());

            return repository.save(existente);

        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar DetalleReceta: " + e.getMessage());
        }
    }
}
