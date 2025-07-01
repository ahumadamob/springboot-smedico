package com.imb2025.smedico.service;

import java.util.List;
import com.imb2025.smedico.entity.Afiliacion;

<<<<<<< Updated upstream
public interface  IAfiliacionService {
	
	public List<Afiliacion> findAll();
	public Afiliacion findById(Long id);
	public Afiliacion save(Afiliacion afiliacion);
	public Afiliacion update(Long id, Afiliacion afiliacion);
	public void deleteById(Long id);
	
=======
public interface IAfiliacionService {
    List<Afiliacion> findAll();
    Afiliacion findById(Long id);
    boolean existsById(Long id); 
    Afiliacion save(Afiliacion afiliacion);
    Afiliacion update(Long id, Afiliacion afiliacion);
    void deleteById(Long id);
>>>>>>> Stashed changes
}
