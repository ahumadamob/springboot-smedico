package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.OrdenEstudioRequestDto;
import com.imb2025.smedico.entity.OrdenEstudio;
import java.util.List;

public interface IOrdenEstudioService {
    public List<OrdenEstudio> findAll();
    public OrdenEstudio create(OrdenEstudio ordenEstudio);
    public OrdenEstudio update(Long id, OrdenEstudio ordenEstudio) throws Exception;
    public OrdenEstudio findById(Long id);
    public void deleteById(Long id);
    public OrdenEstudio fromDto(OrdenEstudioRequestDto ordenEstudioRequestDto) throws Exception;
}
