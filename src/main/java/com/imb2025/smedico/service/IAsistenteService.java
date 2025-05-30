package com.imb2025.smedico.service;
//Service
import java.util.List;
import com.imb2025.smedico.dto.AsistenteRequestDTO;
import com.imb2025.smedico.entity.Asistente;

public interface IAsistenteService {

    public List<Asistente> findAll();
    public Asistente findById(Long id);
    public Asistente create(AsistenteRequestDTO asistenteDTO);
    public void deleteById(Long id);
    public Asistente update(Long id, AsistenteRequestDTO asistenteDTO);
}
