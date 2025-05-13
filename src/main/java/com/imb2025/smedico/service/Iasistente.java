package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.AsistenteEntity;

public interface Iasistente {

	public List<AsistenteEntity> findAll();
	public AsistenteEntity findById(Long id);
	public AsistenteEntity save(AsistenteEntity asistenteentity);
	public void deleteById(Long id);
	public AsistenteEntity update(Long id, AsistenteEntity asistenteActualizado);
}
