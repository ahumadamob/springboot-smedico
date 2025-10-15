package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.MotivoCancelacionRequestDto;
import com.imb2025.smedico.entity.MotivoCancelacion;
import java.util.List;

public interface IMotivoCancelacionService {
    public List<MotivoCancelacion> findAll();
    public MotivoCancelacion create(MotivoCancelacion motivoCancelacion);
    public MotivoCancelacion update(Long id, MotivoCancelacion motivoCancelacion);
    public MotivoCancelacion findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public MotivoCancelacion fromDto(MotivoCancelacionRequestDto motivoCancelacionRequestDto);
	public List<MotivoCancelacion> findByNombre(String nombre);
	public long countByDescripcion(String descripcion);
}
