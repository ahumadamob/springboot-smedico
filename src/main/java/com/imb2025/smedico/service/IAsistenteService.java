package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.AsistenteRequestDto;
import com.imb2025.smedico.entity.Asistente;
import java.util.List;

public interface IAsistenteService {
    public List<Asistente> findAll();
    public Asistente create(Asistente asistente);
    public Asistente update(Long id, Asistente asistente);
    public Asistente findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);

    
    // Métodos mágicos
    List<Asistente> findByApellido(String apellido);
    Long countByNombre(String nombre);
    List<Asistente> findSupervisores();
    List<Asistente> findNoSupervisores();

}