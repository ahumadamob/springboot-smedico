package com.imb2025.smedico.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.MedioPagoRequestDto;
import com.imb2025.smedico.entity.MedioPago;

@Service
public interface IMedioPagoService {
	public List<MedioPago> findAll();
	public MedioPago findById(Long id);
    public MedioPago create(MedioPago medioPago);
    public MedioPago update(Long id, MedioPago medioPago) throws Exception;
    public void deleteById(Long id) throws Exception;
	public MedioPago fromDto(MedioPagoRequestDto dto) throws Exception;  
    
	boolean existsById(Long id);
	
}