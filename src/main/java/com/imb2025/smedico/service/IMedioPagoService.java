package com.imb2025.smedico.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.MedioPagoRequestDTO;
import com.imb2025.smedico.entity.MedioPago;

@Service
public interface IMedioPagoService {
	public List<MedioPago> findAll();
	public MedioPago findById(Long id);
    public MedioPago save(MedioPago medioPago);
    public MedioPago update(Long id, MedioPago medioPago);
    public void deleteById(Long id);
    
	public MedioPago createMedioPago(MedioPagoRequestDTO dto);  
    

	
}
