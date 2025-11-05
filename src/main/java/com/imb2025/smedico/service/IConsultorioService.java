package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.ConsultorioRequestDto;
import com.imb2025.smedico.entity.Consultorio;
import java.util.List;

public interface IConsultorioService {
    public List<Consultorio> findAll();
    public Consultorio create(Consultorio consultorio);
    public Consultorio update(Long id, Consultorio consultorio) throws Exception;
    public Consultorio findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Consultorio findByNombre(String nombre);
    public List<Consultorio> findByUbicacion(String ubicacion);
    public Consultorio fromDto(ConsultorioRequestDto consultorioRequestDto) throws Exception;
}
