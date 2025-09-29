package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.OrdenEstudioRequestDto;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;

import java.time.LocalDate;
import java.util.List;

public interface IOrdenEstudioService {
    public List<OrdenEstudio> findAll();
    public List<OrdenEstudio> findByFecha(LocalDate fecha);
    public long countByMedico(Medico medico);  
    public OrdenEstudio create(OrdenEstudio ordenEstudio);
    public OrdenEstudio update(Long id, OrdenEstudio ordenEstudio) throws Exception;
    public OrdenEstudio findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public OrdenEstudio fromDto(OrdenEstudioRequestDto ordenEstudioRequestDto) throws Exception;
    
}
