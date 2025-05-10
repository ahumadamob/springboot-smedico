package com.imb2025.smedico.service;

import java.util.List;
import com.imb2025.smedico.entity.Turno;

public interface ITurnoService {
    List<Turno> findAll();
    Turno findById(Long id);
    Turno save(Turno turno);
    void deleteById(Long id);
}
