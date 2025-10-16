package com.imb2025.smedico.controller;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.ConsultaRequestDto;
import com.imb2025.smedico.dto.response.ConsultaResponseDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.service.IConsultaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    // ---------- Mapper privado ----------
    private ConsultaResponseDto toResponseDto(Consulta c) {
        return new ConsultaResponseDto(
            c.getId(),
            c.getFecha(),
            c.getDuracionMin(),
            c.getComentarios(),
            c.getTurno() != null ? c.getTurno().getId() : null,
            c.getVersion()   // usa null si aún no tienes @Version
        );
    }

    // ---------- Listado ----------
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<ConsultaResponseDto>>> findAll() {
        List<Consulta> lista = consultaService.findAll();
        List<ConsultaResponseDto> data = lista.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        var resp = new ApiResponseSuccessDto<List<ConsultaResponseDto>>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage(data.isEmpty() ? "No hay consultas" : "Listado de consultas");
        return ResponseEntity.ok(resp);
    }

    // ---------- Detalle por ID ----------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultaResponseDto>> getById(@PathVariable Long id) {
        Consulta c = consultaService.findById(id);

        var resp = new ApiResponseSuccessDto<ConsultaResponseDto>();
        resp.setSuccess(true);
        resp.setData(toResponseDto(c));
        resp.setMessage("Consulta encontrada");
        return ResponseEntity.ok(resp);
    }

    // ---------- Crear ----------
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<ConsultaResponseDto>> create(
            @Valid @RequestBody ConsultaRequestDto dto) {
        Consulta creada = consultaService.createFromDto(dto);

        var resp = new ApiResponseSuccessDto<ConsultaResponseDto>();
        resp.setSuccess(true);
        resp.setData(toResponseDto(creada));
        resp.setMessage("Consulta creada");
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    // ---------- Actualizar ----------
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultaResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody ConsultaRequestDto dto) {

        Consulta actualizada = consultaService.updateFromDto(id, dto);

        var resp = new ApiResponseSuccessDto<ConsultaResponseDto>();
        resp.setSuccess(true);
        resp.setData(toResponseDto(actualizada));
        resp.setMessage("Consulta actualizada");
        return ResponseEntity.ok(resp);
    }

    // ---------- Eliminar ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        consultaService.deleteById(id);

        var resp = new ApiResponseSuccessDto<Void>();
        resp.setSuccess(true);
        resp.setData(null);
        resp.setMessage("Consulta eliminada");
        return ResponseEntity.ok(resp);
    }

    // ---------- Filtro por fecha ----------
    @GetMapping("/filtro-fecha")
    public ResponseEntity<ApiResponseSuccessDto<List<ConsultaResponseDto>>> filtrarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "fecha,desc") String sort
    ) {
        String[] parts = sort.split(",", 2);
        Sort s = (parts.length == 2 && "asc".equalsIgnoreCase(parts[1]))
                ? Sort.by(parts[0]).ascending()
                : Sort.by(parts[0]).descending();

        Pageable pageable = PageRequest.of(page, size, s);
        Page<Consulta> pageResult = consultaService.findByFechaBetween(desde, hasta, pageable);

        List<ConsultaResponseDto> data = pageResult.getContent().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        var resp = new ApiResponseSuccessDto<List<ConsultaResponseDto>>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage(data.isEmpty() ? "Sin resultados en el rango" : "Resultados filtrados por fecha");
        return ResponseEntity.ok(resp);
    }

    // ---------- Conteo por paciente ----------
    @GetMapping("/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> contarPorPaciente(@RequestParam Long pacienteId) {
        long total = consultaService.countByPacienteId(pacienteId);

        var resp = new ApiResponseSuccessDto<Long>();
        resp.setSuccess(true);
        resp.setData(total);
        resp.setMessage("Cantidad de consultas del paciente " + pacienteId);
        return ResponseEntity.ok(resp);
    }
}


