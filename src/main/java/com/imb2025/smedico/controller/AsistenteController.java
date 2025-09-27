package com.imb2025.smedico.controller;
//Controller

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.AsistenteRequestDto;
import com.imb2025.smedico.entity.Asistente;
import com.imb2025.smedico.service.IAsistenteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asistentes")
public class AsistenteController {

    @Autowired
    private IAsistenteService service;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<AsistenteRequestDto>>> findAll() {
        List<Asistente> lista = service.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Convertir entidad -> DTO
        List<AsistenteRequestDto> dtoList = lista.stream().map(a -> {
            AsistenteRequestDto dto = new AsistenteRequestDto();
            dto.setApellido(a.getApellido());
            dto.setNombre(a.getNombre());
            dto.setEmail(a.getEmail());
            dto.setTelefono(a.getTelefono());
            dto.setDni(a.getDni());
            return dto;
        }).toList();

        ApiResponseSuccessDto<List<AsistenteRequestDto>> resp = new ApiResponseSuccessDto<>(
                true,
                "Listado de asistentes obtenido con éxito",
                dtoList
        );
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AsistenteRequestDto>> findById(@PathVariable Long id) {
        Asistente asistente = service.findById(id);

        AsistenteRequestDto dto = new AsistenteRequestDto(
                asistente.getApellido(),
                asistente.getNombre(),
                asistente.getEmail(),
                asistente.getTelefono(),
                asistente.getDni()
        );

        ApiResponseSuccessDto<AsistenteRequestDto> resp = new ApiResponseSuccessDto<>(
                true,
                "Asistente encontrado con éxito",
                dto
        );
        return ResponseEntity.ok(resp);
    }
    
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<AsistenteRequestDto>> create(@Valid @RequestBody AsistenteRequestDto dto) {
        Asistente asistente = service.fromDto(dto);
        Asistente creado = service.create(asistente);

        // Convertimos la entidad creada a DTO
        AsistenteRequestDto respDto = new AsistenteRequestDto(
                creado.getApellido(),
                creado.getNombre(),
                creado.getEmail(),
                creado.getTelefono(),
                creado.getDni()
        );

        ApiResponseSuccessDto<AsistenteRequestDto> resp = new ApiResponseSuccessDto<>(
                true,
                "Asistente creado con éxito",
                respDto
        );
        return ResponseEntity.status(201).body(resp); // 201 CREATED
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AsistenteRequestDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody AsistenteRequestDto dto) {

        Asistente asistente = service.fromDto(dto);
        Asistente actualizado = service.update(id, asistente);

        // Convertimos la entidad actualizada → DTO
        AsistenteRequestDto respDto = new AsistenteRequestDto(
                actualizado.getApellido(),
                actualizado.getNombre(),
                actualizado.getEmail(),
                actualizado.getTelefono(),
                actualizado.getDni()
        );

        ApiResponseSuccessDto<AsistenteRequestDto> resp = new ApiResponseSuccessDto<>(
                true,
                "Asistente actualizado con éxito",
                respDto
        );
        return ResponseEntity.ok(resp);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        service.deleteById(id);

        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>(
                true,
                "Asistente eliminado con éxito",
                null
        );
        return ResponseEntity.ok(resp);
    }
}
