package com.imb2025.smedico.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.OrdenEstudioRequestDto;
import com.imb2025.smedico.dto.response.OrdenEstudioResponseDto;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.mapper.OrdenEstudioMapper;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.service.IOrdenEstudioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordenestudio")
public class OrdenEstudioController {

    private final IOrdenEstudioService service;
    private final MedicoRepository medicoRepository;
    private final OrdenEstudioMapper mapper; // ✅ Inyectar el mapper

    // Constructor con inyección de dependencias
    public OrdenEstudioController(IOrdenEstudioService service, 
                                  MedicoRepository medicoRepository,
                                  OrdenEstudioMapper mapper) {
        this.service = service;
        this.medicoRepository = medicoRepository;
        this.mapper = mapper; // ✅ Inyectar
    }

    // GET - Obtener todas las órdenes de estudio
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> findAllOrdenEstudio() {
        List<OrdenEstudio> ordenes = service.findAll();
        List<OrdenEstudioResponseDto> listaResponse = mapper.toResponseDtoList(ordenes);
        
        String message = ordenes.isEmpty() ? "No hay órdenes de estudio disponibles" : "Lista de órdenes de estudio";
        
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, message, listaResponse));
    }

    // GET - Obtener una orden de estudio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudioResponseDto>> findOrdenEstudioById(@PathVariable Long id) {
        OrdenEstudio orden = service.findById(id);
        OrdenEstudioResponseDto dto = mapper.toDto(orden);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Orden de Estudio encontrada", dto));
    }

    // GET - Obtener órdenes de estudio por fecha
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> findOrdenEstudioByFecha(@PathVariable LocalDate fecha) {
        List<OrdenEstudio> ordenes = service.findByFecha(fecha);
        List<OrdenEstudioResponseDto> listaResponse = mapper.toResponseDtoList(ordenes);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Órdenes encontradas", listaResponse));
    }

    // GET - Obtener la cantidad de órdenes por médico
    @GetMapping("/medico/{idMedico}/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByMedico(@PathVariable Long idMedico) {
        Medico medico = medicoRepository.findById(idMedico)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        long cantidad = service.countByMedico(medico);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Cantidad encontrada", cantidad));
    }

    // GET - Órdenes vigentes
    @GetMapping("/recurso/vigentes")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> getVigentes() {
        List<OrdenEstudio> ordenes = service.findByVigentes(LocalDate.now());
        List<OrdenEstudioResponseDto> lista = mapper.toResponseDtoList(ordenes);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Órdenes de estudio vigentes", lista));
    }

    // GET - Órdenes vencidas
    @GetMapping("/recurso/vencidos")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> getVencidos() {
        List<OrdenEstudio> ordenes = service.findByVencidos(LocalDate.now());
        List<OrdenEstudioResponseDto> lista = mapper.toResponseDtoList(ordenes);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Órdenes de estudio vencidas", lista));
    }

    // POST - Crear una nueva orden de estudio
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudioResponseDto>> createOrdenEstudio(
            @Valid @RequestBody OrdenEstudioRequestDto dto) throws Exception {
        
        OrdenEstudio orden = mapper.fromDto(dto);
        OrdenEstudio ordenGuardada = service.create(orden);
        OrdenEstudioResponseDto responseDto = mapper.toDto(ordenGuardada);
        
        ApiResponseSuccessDto<OrdenEstudioResponseDto> response = 
            new ApiResponseSuccessDto<>(true, "Orden de Estudio creada correctamente", responseDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT - Actualizar una orden de estudio
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudioResponseDto>> updateOrdenEstudio(
            @PathVariable Long id, 
            @Valid @RequestBody OrdenEstudioRequestDto dto) throws Exception {
        
        OrdenEstudio orden = mapper.fromDto(dto);
        OrdenEstudio ordenActualizada = service.update(id, orden);
        OrdenEstudioResponseDto responseDto = mapper.toDto(ordenActualizada);
        
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Orden de Estudio actualizada correctamente", responseDto));
    }

    // DELETE - Eliminar una orden de estudio
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteOrdenEstudio(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Orden de Estudio eliminada correctamente", "Id " + id));
    }
}