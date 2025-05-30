package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.TurnoRequestDTO;
import com.imb2025.smedico.entity.Turno;
import jakarta.validation.constraints.NotNull;

public interface ITurnoService {
  public  List<Turno> findAll();
  public  Turno findById(Long id);
  public Turno save(Turno turno);
  public  void deleteById(Long id);
  public Turno fromDto(TurnoRequestDTO requestDto)throws Exception;
  public Turno fromDto(TurnoRequestDTO dto, Long id) throws Exception;
  public Turno updateFromDto(Turno turnoExistente, TurnoRequestDTO dto) throws Exception;

}
