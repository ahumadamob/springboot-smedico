package com.imb2025.smedico.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.EncuestaRequestDto;
import com.imb2025.smedico.dto.response.EncuestaResponseDto;
import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.mapper.EncuestaMapper;
import com.imb2025.smedico.repository.EncuestaRepository;
import com.imb2025.smedico.service.IEncuestaService;

@RestController
@RequestMapping("/api/encuestas")
public class EncuestaController {

    private final IEncuestaService service;
    private final EncuestaMapper mapper;
    private final EncuestaRepository repo;

    public EncuestaController(IEncuestaService service, EncuestaMapper mapper, EncuestaRepository repo) {
		super();
		this.service = service;
		this.mapper = mapper;
		this.repo = repo;
	}

	public IEncuestaService getService() {
		return service;
	}

	public EncuestaMapper getMapper() {
		return mapper;
	}

	public EncuestaRepository getRepo() {
		return repo;
	}

	

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<EncuestaResponseDto>>> findAll() {
        List<Encuesta> lista = service.findAll();
        List<EncuestaResponseDto> data = new ArrayList<>(lista.size());
        for (Encuesta e : lista) data.add(mapper.toDto(e));
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Listado de encuestas", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EncuestaResponseDto>> findById(@PathVariable Long id) {
        Encuesta e = service.findById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Encuesta encontrada", mapper.toDto(e)));
    }
    @GetMapping("/stats/activos")
    public java.util.Map<String, Long> countActivos() {
        long total = repo.countByEstado(com.imb2025.smedico.entity.Encuesta.Estado.ACTIVO);
        return java.util.Map.of("total", total);
    }

    @GetMapping("/stats/inactivos")
    public java.util.Map<String, Long> countInactivos() {
        long total = repo.countByEstado(com.imb2025.smedico.entity.Encuesta.Estado.INACTIVO);
        return java.util.Map.of("total", total);
    }


    @PostMapping    
    public ResponseEntity<?> create(@Valid @RequestBody EncuestaRequestDto body) {
        // Validación requerida por la consigna (acumular errores)
        List<String> errors = new ArrayList<>();

        if (body.getEstado() == null || body.getEstado().isBlank()) {
            errors.add("estado es requerido (ACTIVO o INACTIVO)");
        } else {
            try {
                com.imb2025.smedico.entity.Encuesta.Estado.valueOf(body.getEstado().trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
                errors.add("estado inválido: solo ACTIVO o INACTIVO");
            }
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(java.util.Map.of("errors", errors));
        }

        // Flujo normal de creación (como ya lo tenías)
        Encuesta entrada = mapper.fromDto(body);
        Encuesta creada = service.create(entrada);
        return ResponseEntity
                .created(URI.create("/api/encuestas/" + creada.getId()))
                .body(new ApiResponseSuccessDto<>(true, "Encuesta creada", mapper.toDto(creada)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EncuestaResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody EncuestaRequestDto body
    ) {
        Encuesta entrada = mapper.fromDto(body);
        Encuesta actualizada = service.update(id, entrada);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Encuesta actualizada", mapper.toDto(actualizada)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/min-puntaje/{n}")
    public ResponseEntity<ApiResponseSuccessDto<List<EncuestaResponseDto>>> findByPuntajeMin(@PathVariable int n) {
        List<EncuestaResponseDto> data = service.findByPuntajeGreaterThanEqual(n)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Encuestas con puntaje >= " + n, data));
    }

    @GetMapping("/consulta/{consultaId}/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByConsulta(@PathVariable Long consultaId) {
        long count = service.countByConsulta(consultaId);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Total por consulta " + consultaId, count));
    }
}
