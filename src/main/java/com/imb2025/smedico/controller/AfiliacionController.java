package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.mapper.AfiliacionMapper;
import com.imb2025.smedico.dto.request.AfiliacionRequestDto;
import com.imb2025.smedico.dto.response.AfiliacionResponseDto;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.service.IAfiliacionService;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/afiliaciones")
public class AfiliacionController {

    private final IAfiliacionService service;
    private final PacienteRepository pacienteRepo;
    private final ObraSocialRepository obraRepo;

    public AfiliacionController(IAfiliacionService service, 
                                PacienteRepository pacienteRepo, 
                                ObraSocialRepository obraRepo) {
        this.service = service;
        this.pacienteRepo = pacienteRepo;
        this.obraRepo = obraRepo;
    }


    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<AfiliacionResponseDto>>> getAllAfiliaciones() {
        List<Afiliacion> lista = service.findAll();
        
        List<AfiliacionResponseDto> dtos = lista.stream()
                .map(AfiliacionMapper::toResponseDto)
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<AfiliacionResponseDto>> resp = 
                new ApiResponseSuccessDto<>(true, "Listado de afiliaciones obtenido con éxito", dtos);

        return ResponseEntity.ok(resp);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> getAfiliacionById(@PathVariable Long id) {
        Afiliacion afiliacion = service.findById(id);
        AfiliacionResponseDto dto = AfiliacionMapper.toResponseDto(afiliacion);

        ApiResponseSuccessDto<AfiliacionResponseDto> resp = 
                new ApiResponseSuccessDto<>(true, "Afiliación encontrada", dto);

        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> createAfiliacion(
            @Valid @RequestBody AfiliacionRequestDto dto) {


        Paciente paciente = pacienteRepo.findById(dto.getIdpaciente())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id " + dto.getIdpaciente()));
        ObraSocial obra = obraRepo.findById(dto.getIdobra())
                .orElseThrow(() -> new ResourceNotFoundException("Obra social no encontrada con id " + dto.getIdobra()));

        Afiliacion entidad = AfiliacionMapper.fromDto(dto, paciente, obra);
        
        Afiliacion creada = service.create(entidad);

        AfiliacionResponseDto respDto = AfiliacionMapper.toResponseDto(creada);

        ApiResponseSuccessDto<AfiliacionResponseDto> resp = 
                new ApiResponseSuccessDto<>(true, "Afiliación creada con éxito", respDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> updateAfiliacion(
            @PathVariable Long id,
            @Valid @RequestBody AfiliacionRequestDto dto) {

        Paciente paciente = pacienteRepo.findById(dto.getIdpaciente())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        ObraSocial obra = obraRepo.findById(dto.getIdobra())
                .orElseThrow(() -> new ResourceNotFoundException("Obra social no encontrada"));

        Afiliacion entidad = AfiliacionMapper.fromDto(dto, paciente, obra);
        
        Afiliacion actualizada = service.update(id, entidad);

        AfiliacionResponseDto respDto = AfiliacionMapper.toResponseDto(actualizada);

        ApiResponseSuccessDto<AfiliacionResponseDto> resp = 
                new ApiResponseSuccessDto<>(true, "Afiliación actualizada con éxito", respDto);

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteAfiliacion(@PathVariable Long id) {
        service.deleteById(id);
        
        ApiResponseSuccessDto<Void> resp = 
                new ApiResponseSuccessDto<>(true, "Afiliación eliminada con éxito", null);
        
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponseSuccessDto<List<AfiliacionResponseDto>>> searchByMinId(
            @RequestParam(name = "minId") Long minId) {
        
        List<Afiliacion> lista = service.findByIdGreaterThan(minId);
        
        List<AfiliacionResponseDto> dtos = lista.stream()
                .map(AfiliacionMapper::toResponseDto)
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<AfiliacionResponseDto>> resp = 
                new ApiResponseSuccessDto<>(true, "Resultados de búsqueda para id > " + minId, dtos);

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByMinId(
            @RequestParam(name = "minId") Long minId) {
        
        long count = service.countByIdGreaterThan(minId);
        
        ApiResponseSuccessDto<Long> resp = 
                new ApiResponseSuccessDto<>(true, "Conteo realizado con éxito", count);

        return ResponseEntity.ok(resp);
    }
}
