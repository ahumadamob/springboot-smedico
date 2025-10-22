package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.DetalleRecetaRequestDto;
import com.imb2025.smedico.dto.response.DetalleRecetaResponseDto;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.mapper.DetalleRecetaMapper;
import com.imb2025.smedico.service.IDetalleRecetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/detalle-recetas")
public class DetalleRecetaController {

    @Autowired
    private IDetalleRecetaService detalleRecetaService;


    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleRecetaResponseDto>>> findAll() {
        List<DetalleReceta> detalles = detalleRecetaService.findAll();
        List<DetalleRecetaResponseDto> detalleResponseDtos = new ArrayList<>();
        DetalleRecetaMapper detalleRecetaMapper = new DetalleRecetaMapper();

        for (DetalleReceta d : detalles) {
            DetalleRecetaResponseDto dto = detalleRecetaMapper.toResponseDto(d);
            detalleResponseDtos.add(dto);
        }

        String mensaje = detalleResponseDtos.isEmpty()
                ? "No hay detalles de recetas disponibles"
                : "Lista de detalles de recetas obtenida correctamente";

        ApiResponseSuccessDto<List<DetalleRecetaResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, mensaje, detalleResponseDtos);
        return ResponseEntity.ok(resp);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleRecetaResponseDto>> findById(@PathVariable Long id) {
        DetalleReceta detalle = detalleRecetaService.findById(id); // usa la entidad para mapear luego
        DetalleRecetaMapper detalleRecetaMapper = new DetalleRecetaMapper();
        DetalleRecetaResponseDto detalleResponse = detalleRecetaMapper.toResponseDto(detalle);

        ApiResponseSuccessDto<DetalleRecetaResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Detalle de receta con ID: " + id + " obtenido correctamente", detalleResponse);

        return ResponseEntity.ok(resp);
    }

 
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DetalleRecetaResponseDto>> createDetalleReceta(
            @Valid @RequestBody DetalleRecetaRequestDto requestDto) {

        DetalleRecetaMapper detalleRecetaMapper = new DetalleRecetaMapper();
        DetalleReceta nuevoDetalle = detalleRecetaMapper.fromDto(requestDto);
        DetalleReceta creado = detalleRecetaService.create(nuevoDetalle);
        DetalleRecetaResponseDto detalleResponse = detalleRecetaMapper.toResponseDto(creado);

        ApiResponseSuccessDto<DetalleRecetaResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Detalle de receta creado correctamente", detalleResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

 
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleRecetaResponseDto>> updateDetalleReceta(
            @PathVariable Long id,
            @Valid @RequestBody DetalleRecetaRequestDto requestDto) {

        DetalleRecetaMapper detalleRecetaMapper = new DetalleRecetaMapper();
        DetalleReceta detalleToUpdate = detalleRecetaMapper.fromDto(requestDto);
        DetalleReceta actualizado = detalleRecetaService.update(id, detalleToUpdate);
        DetalleRecetaResponseDto detalleResponse = detalleRecetaMapper.toResponseDto(actualizado);

        ApiResponseSuccessDto<DetalleRecetaResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Detalle de receta actualizado correctamente", detalleResponse);

        return ResponseEntity.ok(resp);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteDetalleReceta(@PathVariable Long id) {
        detalleRecetaService.deleteById(id);
        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "El detalle de receta con ID: " + id + " fue eliminado correctamente.", "ID eliminado: " + id);
        return ResponseEntity.ok(resp);
    }
    
    @GetMapping("/receta/{recetaId}")
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleRecetaResponseDto>>> findByRecetaId(@PathVariable Long recetaId){
    	List<DetalleReceta> detalles = detalleRecetaService.findByRecetaId(recetaId);
        List<DetalleRecetaResponseDto> detalleResponseDtos = new ArrayList<>();
        DetalleRecetaMapper detalleRecetaMapper = new DetalleRecetaMapper();

        for (DetalleReceta d : detalles) {
            DetalleRecetaResponseDto dto = detalleRecetaMapper.toResponseDto(d);
            detalleResponseDtos.add(dto);
        }

        String mensaje = detalleResponseDtos.isEmpty()
                ? "No hay detalles de recetas disponibles"
                : "Lista de detalles de recetas obtenida correctamente";

        ApiResponseSuccessDto<List<DetalleRecetaResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, mensaje, detalleResponseDtos);
        return ResponseEntity.ok(resp);
    	
    }
    	

    @GetMapping("/count/{medicamentoId}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByMedicamentoId(@PathVariable Long medicamentoId) {
        Long cantidad = detalleRecetaService.countByMedicamentoId(medicamentoId);
        ApiResponseSuccessDto<Long> resp =
                new ApiResponseSuccessDto<>(true, "Cantidad de detalles asociados al medicamento con ID: " + medicamentoId, cantidad);
        return ResponseEntity.ok(resp);
    }
   


    @GetMapping("/controlados")
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleRecetaResponseDto>>> getControlados() {
        List<DetalleReceta> detalles = detalleRecetaService.findByControladoTrue();
        List<DetalleRecetaResponseDto> detalleResponseDtos = new ArrayList<>();
        DetalleRecetaMapper detalleRecetaMapper = new DetalleRecetaMapper();

        for (DetalleReceta d : detalles) {
            detalleResponseDtos.add(detalleRecetaMapper.toResponseDto(d));
        }

        String mensaje = detalleResponseDtos.isEmpty()
                ? "No hay detalles de recetas controladas disponibles"
                : "Lista de detalles de recetas controladas obtenida correctamente";

        ApiResponseSuccessDto<List<DetalleRecetaResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, mensaje, detalleResponseDtos);
        return ResponseEntity.ok(resp);
    }

  
    @GetMapping("/no-controlados")
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleRecetaResponseDto>>> getNoControlados() {
        List<DetalleReceta> detalles = detalleRecetaService.findByControladoFalse();
        List<DetalleRecetaResponseDto> detalleResponseDtos = new ArrayList<>();
        DetalleRecetaMapper detalleRecetaMapper = new DetalleRecetaMapper();

        for (DetalleReceta d : detalles) {
            detalleResponseDtos.add(detalleRecetaMapper.toResponseDto(d));
        }

        String mensaje = detalleResponseDtos.isEmpty()
                ? "No hay detalles de recetas no controladas disponibles"
                : "Lista de detalles de recetas no controladas obtenida correctamente";

        ApiResponseSuccessDto<List<DetalleRecetaResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, mensaje, detalleResponseDtos);
        return ResponseEntity.ok(resp);
    }
}

