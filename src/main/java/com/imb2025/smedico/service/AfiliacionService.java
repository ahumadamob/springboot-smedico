package com.imb2025.smedico.service;

import java.util.List;
import com.imb2025.smedico.entity.Afiliacion;

public interface  AfiliacionService {
	
	public List<Afiliacion> findAll();
	public Afiliacion findById(Long id);
	public Afiliacion save(Afiliacion afiliacion);
	public Afiliacion update(Long id, Afiliacion afiliacion);
	public void deleteById(Long id);
	
}
