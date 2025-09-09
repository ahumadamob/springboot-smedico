package com.imb2025.smedico.service;

import java.util.List;
import com.imb2025.smedico.entity.SignosVitales;
import com.imb2025.smedico.dto.SignosVitalesRequestDto;

public interface ISignosVitalesService {
	public List<SignosVitales> findAll();
	public SignosVitales create(SignosVitales signosVitales) throws Exception;
	public SignosVitales update(Long id, SignosVitales signosVitales) throws Exception;
	public SignosVitales findById(Long id);
	public boolean existsById(Long id);
	public void deleteById(Long id);
	public SignosVitales fromDto(SignosVitalesRequestDto signosVitalesRequestDto) throws Exception;

}