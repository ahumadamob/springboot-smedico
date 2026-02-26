package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.request.MedioPagoRequestDto;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.MedioPago.TipoPago;


public interface IMedioPagoService {

	public List<MedioPago> findAll();
	public MedioPago findById(Long id);
	public MedioPago save(MedioPago mediopago);
	public MedioPago update (Long id, MedioPago mediopago) throws Exception;
	public void deleteById(Long id) throws Exception;
	public MedioPago crearMedioPago(MedioPagoRequestDto dtor) throws Exception;
	public boolean existById (Long id);
	public List<MedioPago> findByTipo (TipoPago tipo);
	public Long countByNombre (String nombre);
	

}

