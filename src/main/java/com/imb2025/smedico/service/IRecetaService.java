package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.RecetaRequestDto;
import com.imb2025.smedico.entity.Receta;
import java.util.List;

public interface IRecetaService {
    public List<Receta> findAll();
    public Receta create(Receta receta);
    public Receta update(Long id, Receta receta) throws Exception;
    public Receta findById(Long id);
    public void deleteById(Long id);
    public Receta fromDto(RecetaRequestDto recetaRequestDto) throws Exception;
}
