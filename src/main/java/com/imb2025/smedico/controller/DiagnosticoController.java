package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseErrorDto;
import com.imb2025.smedico.dto.FieldErrorDto;
import com.imb2025.smedico.dto.mapper.DiagnosticoMapper;
import com.imb2025.smedico.dto.request.DiagnosticoRequestDto;
import com.imb2025.smedico.dto.response.DiagnosticoResponseDto;
import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.service.IDiagnosticoService;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Validated
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    private final IDiagnosticoService service;

    public DiagnosticoController(IDiagnosticoService service) {
        this.service = service;
    }

    // GET /diagnosticos
    @GetMapping
    public ResponseEntity<List<DiagnosticoResponseDto>> getAll() {
        List<DiagnosticoResponseDto> out = service.findAll()
                .stream().map(DiagnosticoMapper::toResponseDto).collect(toList());
        if (out.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(out);
    }

    // GET /diagnosticos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticoResponseDto> getById(@PathVariable Long id) {
        Diagnostico data = service.findById(id);
        return ResponseEntity.ok(DiagnosticoMapper.toResponseDto(data));
    }

    // POST /diagnosticos (Ej.2: control duplicado)
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DiagnosticoRequestDto dto) {
        if (service.existsByIdentificadorLegibleIgnoreCase(dto.getIdentificadorLegible())) {
            // usar el mismo DTO que maneja el profe
            FieldErrorDto error = new FieldErrorDto("identificadorLegible", "identificadorLegible duplicado");
            ApiResponseErrorDto response = new ApiResponseErrorDto(false, List.of(error));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Diagnostico guardado = service.create(DiagnosticoMapper.fromDto(dto));
        DiagnosticoResponseDto body = DiagnosticoMapper.toResponseDto(guardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }


    // PUT /diagnosticos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticoResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody DiagnosticoRequestDto dto) {
        Diagnostico actualizado = service.update(id, DiagnosticoMapper.fromDto(dto));
        return ResponseEntity.ok(DiagnosticoMapper.toResponseDto(actualizado));
    }

    // DELETE /diagnosticos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- TP7 (los tuyos, para que no rompa nada) ----------
    @GetMapping("/find/{fecha}")
    public ResponseEntity<List<DiagnosticoResponseDto>> getDiagnosticosPorFecha(
            @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        List<DiagnosticoResponseDto> lista = service.findByFechaDiagnostico(fecha)
                .stream().map(DiagnosticoMapper::toResponseDto).collect(toList());
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/count/{fecha}")
    public ResponseEntity<Long> countDiagnosticosPorFecha(
            @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(service.countByFechaDiagnostico(fecha));
    }

    // ---------- Parcial: Ejercicio 3 ----------
    @GetMapping("/vigentes")
    public ResponseEntity<List<DiagnosticoResponseDto>> vigentes() {
        List<DiagnosticoResponseDto> out = service.findVigentes(LocalDate.now())
                .stream().map(DiagnosticoMapper::toResponseDto).collect(toList());
        return ResponseEntity.ok(out); // lista vacía = 200 OK como pide la consigna
    }

    @GetMapping("/vencidos")
    public ResponseEntity<List<DiagnosticoResponseDto>> vencidos() {
        List<DiagnosticoResponseDto> out = service.findVencidos(LocalDate.now())
                .stream().map(DiagnosticoMapper::toResponseDto).collect(toList());
        return ResponseEntity.ok(out);
    }
}
