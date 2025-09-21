package com.imb2025.smedico.controller;

<<<<<<< Updated upstream
import com.imb2025.smedico.dto.ApiResponseErrorDto;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.DetalleRecetaRequestDto;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.service.IDetalleRecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
=======
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "detalle_receta")
public class DetalleReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
>>>>>>> Stashed changes

@RestController
@RequestMapping("/detalleReceta")
public class DetalleRecetaController {

    @Autowired
    private IDetalleRecetaService service;

<<<<<<< Updated upstream
    // Método utilitario para construir respuestas estándar de éxito
    private <T> ResponseEntity<ApiResponseSuccessDto<T>> buildSuccess(T data, String message, HttpStatus status) {
        ApiResponseSuccessDto<T> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage(message);
        return ResponseEntity.status(status).body(resp);
    }

    // Listar todas las DetalleRecetas
    @Operation(summary = "Listar todas las DetalleRecetas")
    @ApiResponse(responseCode = "200", description = "Listado de DetalleRecetas obtenido con éxito")
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleReceta>>> findAll() {
        List<DetalleReceta> detalles = service.findAll();
        return buildSuccess(detalles, "Listado de DetalleRecetas obtenido con éxito", HttpStatus.OK);
    }

    // Obtener DetalleReceta por ID
    @Operation(summary = "Obtener DetalleReceta por ID")
    @ApiResponse(responseCode = "200", description = "DetalleReceta encontrada con éxito")
    @ApiResponse(responseCode = "404", description = "DetalleReceta no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        DetalleReceta detalle = service.findById(id);
        if (detalle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseErrorDto("DetalleReceta no encontrada"));
        }
        return buildSuccess(detalle, "DetalleReceta encontrada con éxito", HttpStatus.OK);
    }

    // Crear nueva DetalleReceta
    @Operation(summary = "Crear nueva DetalleReceta")
    @ApiResponse(responseCode = "201", description = "DetalleReceta creada con éxito")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> save(@Valid @RequestBody DetalleRecetaRequestDto dto) {
        DetalleReceta nueva = service.create(service.convertFromDto(dto));
        return buildSuccess(nueva, "DetalleReceta creada con éxito", HttpStatus.CREATED);
    }

    // Actualizar DetalleReceta por ID
    @Operation(summary = "Actualizar DetalleReceta por ID")
    @ApiResponse(responseCode = "200", description = "DetalleReceta actualizada con éxito")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @ApiResponse(responseCode = "404", description = "DetalleReceta no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody DetalleRecetaRequestDto dto) {
        DetalleReceta existente = service.findById(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseErrorDto("DetalleReceta no encontrada"));
        }
        DetalleReceta actualizada = service.update(id, service.convertFromDto(dto));
        return buildSuccess(actualizada, "DetalleReceta actualizada con éxito", HttpStatus.OK);
    }

    // Eliminar DetalleReceta por ID
    @Operation(summary = "Eliminar DetalleReceta por ID")
    @ApiResponse(responseCode = "200", description = "DetalleReceta eliminada con éxito")
    @ApiResponse(responseCode = "404", description = "DetalleReceta no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        DetalleReceta existente = service.findById(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseErrorDto("DetalleReceta no encontrada"));
        }
        service.deleteById(id);
        return buildSuccess(null, "DetalleReceta eliminada con éxito", HttpStatus.OK);
    }
=======
    @Column(nullable = false)
    private String dosis;

    @Column(nullable = false)
    private String frecuencia;

    // Constructor vacío obligatorio para JPA
    public DetalleReceta() {}

    // Constructor sin ID (porque lo genera la DB)
    public DetalleReceta(Receta receta, Medicamento medicamento, String dosis, String frecuencia) {
        this.receta = receta;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Receta getReceta() { return receta; }
    public void setReceta(Receta receta) { this.receta = receta; }

    public Medicamento getMedicamento() { return medicamento; }
    public void setMedicamento(Medicamento medicamento) { this.medicamento = medicamento; }

    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }
>>>>>>> Stashed changes
}
