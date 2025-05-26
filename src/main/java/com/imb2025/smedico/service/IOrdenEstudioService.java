package com.imb2025.smedico.service;

import java.util.List;


import com.imb2025.smedico.entity.OrdenEstudio;

public interface IOrdenEstudioService {

	public List<OrdenEstudio> findAll();
	public OrdenEstudio findById(Long id);
	public OrdenEstudio save(OrdenEstudio ordenestudio);
	public void deleteById(Long id);
	
	
	
}
