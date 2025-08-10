package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.dto.OrdenEstudioRequestDto;
public interface IOrdenEstudioService {

	public List<OrdenEstudio> findAll();
	public OrdenEstudio findById(Long id);
	public OrdenEstudio create(OrdenEstudio ordenestudio);
	public OrdenEstudio update(Long id,OrdenEstudio ordenestudio) throws Exception;
	public void deleteById(Long id);
    public OrdenEstudio fromDto(OrdenEstudioRequestDto requestDto) throws Exception;
    boolean existsById(Long id);
}
