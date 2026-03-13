package com.imb2025.smedico.service;


import com.imb2025.smedico.entity.Diagnostico;

import java.time.LocalDate;
import java.util.List;

public interface IDiagnosticoService {
    List<Diagnostico> findAll();
    Diagnostico findById(Long id);
    Diagnostico create(Diagnostico diagnostico);
    Diagnostico update(Long id, Diagnostico diagnostico);
    void deleteById(Long id);
    boolean existsById(Long id);


    
    List<Diagnostico> findByFechaDiagnostico(LocalDate fechaDiagnostico);

    long countByFechaDiagnostico(LocalDate fechaDiagnostico);
    
    boolean existsByCodigoReferenciaIgnoreCase(String codigoReferencia);
	Diagnostico update1(Long id, Diagnostico diagnostico);
	Diagnostico codigoReferencia(Long id, Diagnostico diagnostico);

}
