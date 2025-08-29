package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.EstudioRequestDto;
import com.imb2025.smedico.entity.Estudio;
import java.util.List;

public interface IEstudioService {
    public List<Estudio> findAll();
    public Estudio create(Estudio estudio) throws Exception;
    public Estudio update(Long id, Estudio estudio) throws Exception;
    public Estudio findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Estudio fromDto(EstudioRequestDto estudioRequestDto) throws Exception;
}
