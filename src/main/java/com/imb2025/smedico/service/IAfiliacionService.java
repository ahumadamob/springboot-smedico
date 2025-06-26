package com.imb2025.smedico.service;

import java.util.List;
import com.imb2025.smedico.entity.Afiliacion;

public interface  IAfiliacionService {
	
	List<Afiliacion> findAll();
    Afiliacion findById(Long id);
    Afiliacion save(Afiliacion afiliacion);
    Afiliacion update(Long id, Afiliacion afiliacion);
    void deleteById(Long id);
    boolean existsById(Long id);
	
}
