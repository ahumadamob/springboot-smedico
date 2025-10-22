package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.AsistenteRequestDto;
import com.imb2025.smedico.dto.response.AsistenteResponseDto;
import com.imb2025.smedico.entity.Asistente;
import com.imb2025.smedico.mapper.AsistenteMapper;
import com.imb2025.smedico.service.IAsistenteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asistentes")
public class AsistenteController {

    @Autowired
    private IAsistenteService service;

    private AsistenteMapper mapper = new AsistenteMapper();

    private ResponseEntity<Object> handleValidationErrors(BindingResult result) {
        List<String> errores = result.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            java.util.Collections.singletonMap("errors", errores)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<AsistenteResponseDto>>> findAll() {
        List<Asistente> lista = service.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AsistenteResponseDto> dtoList = lista.stream()
                .map(mapper::toDto)
                .toList();

        ApiResponseSuccessDto<List<AsistenteResponseDto>> resp = new ApiResponseSuccessDto<>(
                true,
                "Listado de asistentes obtenido con éxito",
                dtoList
        );
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AsistenteResponseDto>> findById(@PathVariable Long id) {
        Asistente asistente = service.findById(id);
        AsistenteResponseDto dto = mapper.toDto(asistente);

        ApiResponseSuccessDto<AsistenteResponseDto> resp = new ApiResponseSuccessDto<>
        (true,"Asistente encontrado con éxito",dto);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<?> create( 
            @Valid @RequestBody AsistenteRequestDto dto,
            BindingResult result) { 

        if (result.hasErrors()) {
            return handleValidationErrors(result); 
        }

        Asistente asistente = mapper.fromDto(dto);
        Asistente creado = service.create(asistente);
        AsistenteResponseDto respDto = mapper.toDto(creado);

        ApiResponseSuccessDto<AsistenteResponseDto> resp = new ApiResponseSuccessDto<>(
                true,
                "Asistente creado con éxito",
                respDto
        );
        return ResponseEntity.status(201).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update( 
            @PathVariable Long id,
            @Valid @RequestBody AsistenteRequestDto dto,
            BindingResult result) { 

        if (result.hasErrors()) {
            return handleValidationErrors(result);
        }

        Asistente asistente = mapper.fromDto(dto);
        Asistente actualizado = service.update(id, asistente);
        AsistenteResponseDto respDto = mapper.toDto(actualizado);

        ApiResponseSuccessDto<AsistenteResponseDto> resp = new ApiResponseSuccessDto<>(
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

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<ApiResponseSuccessDto<List<AsistenteResponseDto>>> findByApellido(@PathVariable String apellido) {
        List<Asistente> lista = service.findByApellido(apellido);

        List<AsistenteResponseDto> dtoList = lista.stream()
                .map(mapper::toDto)
                .toList();

        String mensaje = lista.isEmpty() ? "No se encontraron asistentes con apellido " + apellido
                                         : "Asistentes con apellido " + apellido;

        ApiResponseSuccessDto<List<AsistenteResponseDto>> resp = new ApiResponseSuccessDto<>(
                true, mensaje, dtoList
        );
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/count/{nombre}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByNombre(@PathVariable String nombre) {
        Long cantidad = service.countByNombre(nombre);

        String mensaje = "Cantidad de asistentes con nombre " + nombre + ": " + cantidad;

        ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(
                true, mensaje, cantidad
        );
        return ResponseEntity.ok(resp);
    }
    
    @GetMapping("/supervisores") 
    public ResponseEntity<ApiResponseSuccessDto<List<AsistenteResponseDto>>> findSupervisores() {
        List<Asistente> lista = service.findSupervisores();

        List<AsistenteResponseDto> dtoList = lista.stream()
                .map(mapper::toDto)
                .toList();

        String mensaje = lista.isEmpty() ? "No se encontraron asistentes supervisores"
                                         : "Listado de asistentes supervisores obtenido con éxito";

        ApiResponseSuccessDto<List<AsistenteResponseDto>> resp = new ApiResponseSuccessDto<>(
                true, mensaje, dtoList
        );
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/no-supervisores") 
    public ResponseEntity<ApiResponseSuccessDto<List<AsistenteResponseDto>>> findNoSupervisores() {
        List<Asistente> lista = service.findNoSupervisores();

        List<AsistenteResponseDto> dtoList = lista.stream()
                .map(mapper::toDto)
                .toList();
        
        String mensaje = lista.isEmpty() ? "No se encontraron asistentes no supervisores"
                                         : "Listado de asistentes no supervisores obtenido con éxito";

        ApiResponseSuccessDto<List<AsistenteResponseDto>> resp = new ApiResponseSuccessDto<>(
                true, mensaje, dtoList
        );
        return ResponseEntity.ok(resp);
    }
}
