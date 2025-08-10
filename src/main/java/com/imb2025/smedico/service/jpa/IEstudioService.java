package com.imb2025.smedico.service.jpa;

import java.util.List;
import com.imb2025.smedico.entity.Estudio;

public interface IEstudioService {
    List<Estudio> findAll();
    Estudio findById(Long id);
    Estudio save(Estudio estudio);
    void deleteById(Long id);
}

