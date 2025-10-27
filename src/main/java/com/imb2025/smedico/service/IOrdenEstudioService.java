package com.imb2025.smedico.service;


import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;

import java.time.LocalDate;
import java.util.List;

public interface IOrdenEstudioService {

    // Devuelve todas las órdenes de estudio
    List<OrdenEstudio> findAll();

    List<OrdenEstudio> findByFecha(LocalDate fecha);
    long countByMedico(Medico medico);  
  
    // Crea una nueva orden de estudio
    OrdenEstudio create(OrdenEstudio ordenEstudio);
     
    // Actualiza una orden existente por ID
    OrdenEstudio update(Long id, OrdenEstudio ordenEstudio) throws Exception;

    // Busca una orden por su ID
    OrdenEstudio findById(Long id);

    // Verifica si existe una orden por ID
    boolean existsById(Long id);

    // Elimina una orden por ID
    void deleteById(Long id);


}
