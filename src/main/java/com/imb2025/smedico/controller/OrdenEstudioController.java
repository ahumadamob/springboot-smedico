package com.imb2025.smedico.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IOrdenEstudioService service;
    
    @Autowired
    private MedicoRepository medicoRepository;
    

    @Autowired
    private OrdenEstudioMapper mapper;


    // GET - Obtener todas las órdenes de estudio
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> findAllOrdenEstudio() {
        List<OrdenEstudio> ordenes = service.findAll();
        List<OrdenEstudioResponseDto> listaResponse = ordenes.stream()
                .map(mapper::toDto)
                .toList();

        String message = listaResponse.isEmpty() ?
                "No hay órdenes de estudio disponibles" :
                "Lista de órdenes de estudio";

        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, message, listaResponse));
    }

    // GET - Obtener una orden de estudio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudioResponseDto>> findOrdenEstudioById(@PathVariable("id") Long id) {
        OrdenEstudio orden = service.findById(id);
        return ResponseEntity.ok(
                new ApiResponseSuccessDto<>(true, "Orden encontrada", mapper.toDto(orden))
        );
    }

 // GET - Obtener órdenes de estudio por fecha
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudio>>> findOrdenEstudioByFecha(@PathVariable LocalDate fecha) {
        List<OrdenEstudio> ordenes = service.findByFecha(fecha);
        ApiResponseSuccessDto<List<OrdenEstudio>> resp = new ApiResponseSuccessDto<>(true, "Órdenes encontradas", ordenes);
        return ResponseEntity.ok(resp);
    }

    // GET - Obtener la cantidad de órdenes por médico
    @GetMapping("/medico/{idMedico}/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByMedico(@PathVariable Long idMedico) {
        Medico medico = medicoRepository.findById(idMedico)
            .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        long cantidad = service.countByMedico(medico);
        ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(true, "Cantidad encontrada", cantidad);
        return ResponseEntity.ok(resp);
    }

    
    // GET - Obtener la cantidad de órdenes por médico
    @GetMapping("/recurso/vigentes")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> getVigentes() {
        List<OrdenEstudio> ordenes = service.findByFechaVigenciaGreaterThanEqual(LocalDate.now());
        List<OrdenEstudioResponseDto> lista = mapper.toResponseDtoList(ordenes);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Órdenes de estudio vigentes", lista));
    
    }

    // GET - Órdenes vencidas
    @GetMapping("/recurso/vencidos")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> getVencidos() {
        List<OrdenEstudio> ordenes = service.findByFechaVigenciaLessThan(LocalDate.now());
        List<OrdenEstudioResponseDto> lista = mapper.toResponseDtoList(ordenes);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Órdenes de estudio vencidas", lista));
    }
    

    // POST - Crear una nueva orden de estudio
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudioResponseDto>> createOrdenEstudio(
            @Valid @RequestBody OrdenEstudioRequestDto dto) throws Exception {

        if (service.existsByCodigoOrdenIgnoreCase(dto.getCodigoOrden())) {
            throw new BadRequestException("codigoOrden duplicado");
        }

        OrdenEstudio guardada = service.create(mapper.fromDto(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseSuccessDto<>(true, "Orden creada correctamente", mapper.toDto(guardada)));
    }

    // PUT - Actualizar una orden de estudio
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudioResponseDto>> updateOrdenEstudio(
            @PathVariable Long id,
            @Valid @RequestBody OrdenEstudioRequestDto dto) throws Exception {

        OrdenEstudio orden = mapper.fromDto(dto);
        OrdenEstudio actualizada = service.update(id, orden);

        return ResponseEntity.ok(
                new ApiResponseSuccessDto<>(true, "Orden actualizada correctamente", mapper.toDto(actualizada))
        );
    }

    // DELETE - Eliminar una orden de estudio
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteOrdenEstudio(@PathVariable Long id) {
        service.deleteById(id);

        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "Orden de Estudio eliminada correctamente", "Id " + id);

        return ResponseEntity.ok(resp);
    }
}
