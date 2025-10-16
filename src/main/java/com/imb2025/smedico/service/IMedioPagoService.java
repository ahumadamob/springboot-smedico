package com.imb2025.smedico.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.request.MedioPagoRequestDto;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.MedioPago.TipoPago;

@Service
public interface IMedioPagoService {
	public List<MedioPago> findAll();
	public MedioPago findById(Long id);
    public MedioPago create(MedioPago medioPago);
    public MedioPago update(Long id, MedioPago medioPago) throws Exception;
    public void deleteById(Long id) throws Exception;
	public MedioPago fromDto(MedioPagoRequestDto dto) throws Exception;  
	List<MedioPago> findByTipo(TipoPago tipo);//a
	Long countByNombre(String nombre);//a
	boolean existsById(Long id);
	
}