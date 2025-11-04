package com.imb2025.smedico.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.MedioPago.TipoPago;

@Service
public interface IMedioPagoService {
	public List<MedioPago> findAll();
	public MedioPago findById(Long id);
    public MedioPago create(MedioPago medioPago);
    public MedioPago update(Long id, MedioPago medioPago);
    public void deleteById(Long id);
	List<MedioPago> findByTipo(TipoPago tipo);
	Long countByNombre(String nombre);
	boolean existsById(Long id);
	
}
