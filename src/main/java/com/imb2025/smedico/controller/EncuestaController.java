package com.imb2025.smedico.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.service.IEncuestaService;

@RestController
@RequestMapping("/api/encuestas")
public class EncuestaController {

    private final IEncuestaService service;

    public EncuestaController(IEncuestaService service) {
        this.service = service;
    }

    /** DTO de salida local (simple) */
    public static class EncuestaOut {
        private Long id;
        private Long pacienteId;
        private Long consultaId;
        private Integer puntaje;
        private String comentario;

        public EncuestaOut(Long id, Long pacienteId, Long consultaId, Integer puntaje, String comentario) {
            this.id = id;
            this.pacienteId = pacienteId;
            this.consultaId = consultaId;
            this.puntaje = puntaje;
            this.comentario = comentario;
        }

        public Long getId() { return id; }
        public Long getPacienteId() { return pacienteId; }
        public Long getConsultaId() { return consultaId; }
        public Integer getPuntaje() { return puntaje; }
        public String getComentario() { return comentario; }
    }

    private EncuestaOut toOut(Encuesta e) {
        return new EncuestaOut(
                e.getId(),
                e.getPaciente() != null ? e.getPaciente().getId() : null,
                e.getConsulta() != null ? e.getConsulta().getId() : null,
                e.getPuntaje(),
                e.getComentario()
        );
    }

    /* ================== CRUD ================== */

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<EncuestaOut>>> findAll() {
        List<EncuestaOut> data = service.findAll()
                .stream()
                .map(this::toOut)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Listado de encuestas", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EncuestaOut>> findById(@PathVariable Long id) {
        Encuesta e = service.findById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Encuesta encontrada", toOut(e)));
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<EncuestaOut>> create(@Valid @RequestBody EncuestaRequestDto body) {
        Encuesta creada = service.create(service.fromDto(body));
        return ResponseEntity
                .created(URI.create("/api/encuestas/" + creada.getId()))
                .body(new ApiResponseSuccessDto<>(true, "Encuesta creada", toOut(creada)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EncuestaOut>> update(
            @PathVariable Long id,
            @Valid @RequestBody EncuestaRequestDto body
    ) {
        Encuesta actualizada = service.update(id, service.fromDto(body));
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Encuesta actualizada", toOut(actualizada)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    /* ============ TP07: métodos “mágicos” expuestos ============ */

    @GetMapping("/min-puntaje/{n}")
    public ResponseEntity<ApiResponseSuccessDto<List<EncuestaOut>>> findByPuntajeMin(@PathVariable int n) {
        List<EncuestaOut> data = service.findByPuntajeGreaterThanEqual(n)
                .stream()
                .map(this::toOut)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Encuestas con puntaje >= " + n, data));
    }

    @GetMapping("/consulta/{consultaId}/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByConsulta(@PathVariable Long consultaId) {
        long count = service.countByConsulta(consultaId);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Total por consulta " + consultaId, count));
    }
}

