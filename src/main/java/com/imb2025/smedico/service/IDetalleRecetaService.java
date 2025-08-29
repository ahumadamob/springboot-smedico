package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DetalleRecetaRequestDto;
import com.imb2025.smedico.entity.DetalleReceta;
import java.util.List;

public interface IDetalleRecetaService {
    public List<DetalleReceta> findAll();
    public DetalleReceta create(DetalleReceta detalleReceta);
    public DetalleReceta update(Long id, DetalleReceta detalleReceta);
    public DetalleReceta findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public DetalleReceta fromDto(DetalleRecetaRequestDto detalleRecetaRequestDto);
}
