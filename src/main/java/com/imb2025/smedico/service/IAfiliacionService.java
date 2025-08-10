package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.AfiliacionRequestDto;
import com.imb2025.smedico.entity.Afiliacion;
import java.util.List;

public interface IAfiliacionService {
    public List<Afiliacion> findAll();
    public Afiliacion create(Afiliacion afiliacion) throws Exception;
    public Afiliacion update(Long id, Afiliacion afiliacion) throws Exception;
    public Afiliacion findById(Long id);
    public void deleteById(Long id);
    public Afiliacion fromDto(AfiliacionRequestDto afiliacionRequestDto) throws Exception;
}
