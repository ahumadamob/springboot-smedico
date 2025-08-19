package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.ResultadoEstudioRequestDto;
import com.imb2025.smedico.entity.ResultadoEstudio;
import java.util.List;

public interface IResultadoEstudioService {
    public List<ResultadoEstudio> findAll();
    public ResultadoEstudio create(ResultadoEstudio resultadoEstudio);
    public ResultadoEstudio update(Long id, ResultadoEstudio resultadoEstudio) throws Exception;
    public ResultadoEstudio findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public ResultadoEstudio fromDto(ResultadoEstudioRequestDto resultadoEstudioRequestDto) throws Exception;
}
