package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.MotivoCancelacion;

public interface IMotivoCancelacionService {
	
	public List<MotivoCancelacion> findAll();
	public MotivoCancelacion findById(Long id);
	public MotivoCancelacion save(MotivoCancelacion motivocancelacion);
	public void deleteById(Long id);
}
