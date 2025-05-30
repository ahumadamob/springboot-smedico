package com.imb2025.smedico.service.jpa;

import java.util.List;
import com.imb2025.smedico.entity.EstudioEntity;

public interface IEstudioService {
    List<EstudioEntity> findAll();
    EstudioEntity findById(Long id);
    EstudioEntity save(EstudioEntity estudio);
    void deleteById(Long id);
}

