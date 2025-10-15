package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DetalleRecetaRequestDto;
import com.imb2025.smedico.entity.DetalleReceta;
import java.util.List;

public interface IDetalleRecetaService {

    List<DetalleReceta> findAll();

    DetalleReceta create(DetalleReceta detalleReceta);

    DetalleReceta update(Long id, DetalleReceta detalleReceta);

    DetalleReceta findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    DetalleReceta fromDto(DetalleRecetaRequestDto detalleRecetaRequestDto);

    
    List<DetalleReceta> findByRecetaId(Long recetaId);


    Long countByMedicamentoId(Long medicamentoId);
}
