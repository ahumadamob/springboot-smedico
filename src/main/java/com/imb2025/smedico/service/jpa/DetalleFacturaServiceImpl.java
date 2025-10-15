package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.request.DetalleFacturaRequestDto;
import com.imb2025.smedico.dto.response.DetalleFacturaResponseDto;
import com.imb2025.smedico.entity.DetalleFactura;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.mapper.DetalleFacturaMapper;
import com.imb2025.smedico.repository.DetalleFacturaRepository;
import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.service.IDetalleFacturaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleFacturaServiceImpl implements IDetalleFacturaService {

    private final DetalleFacturaRepository repo;
    private final FacturaRepository facturaRepo;

    public DetalleFacturaServiceImpl(DetalleFacturaRepository repo, FacturaRepository facturaRepo) {
        this.repo = repo;
        this.facturaRepo = facturaRepo;
    }

    @Override
    public List<DetalleFacturaResponseDto> findAll() {
        return repo.findAll().stream()
                .map(DetalleFacturaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public DetalleFacturaResponseDto findById(Long id) {
        DetalleFactura detalle = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleFactura no encontrada con id " + id));
        return DetalleFacturaMapper.toResponseDto(detalle);
    }

    @Override
    public DetalleFacturaResponseDto create(DetalleFacturaRequestDto dto) {
        Factura factura = facturaRepo.findById(dto.getFacturaId())
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id " + dto.getFacturaId()));
        DetalleFactura detalle = DetalleFacturaMapper.fromDto(dto, factura);
        DetalleFactura guardado = repo.save(detalle);
        return DetalleFacturaMapper.toResponseDto(guardado);
    }

    @Override
    public DetalleFacturaResponseDto update(Long id, DetalleFacturaRequestDto dto) {
        DetalleFactura existente = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleFactura no encontrada con id " + id));
        Factura factura = facturaRepo.findById(dto.getFacturaId())
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id " + dto.getFacturaId()));
        existente.setDescripcion(dto.getDescripcion());
        existente.setImporte(dto.getImporte() != null ? BigDecimal.valueOf(dto.getImporte()) : null);
        existente.setFactura(factura);
        DetalleFactura actualizado = repo.save(existente);
        return DetalleFacturaMapper.toResponseDto(actualizado);
    }
}


