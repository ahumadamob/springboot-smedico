package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.dto.DetalleRecetaRequestDto;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.service.IDetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleReceta")
public class DetalleRecetaController {

    @Autowired
    private IDetalleRecetaService service;

   
    @GetMapping("/receta/{recetaId}")
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleReceta>>> findByRecetaId(@PathVariable Long recetaId) {
        List<DetalleReceta> detalles = service.findByRecetaId(recetaId);

        ApiResponseSuccessDto<List<DetalleReceta>> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(detalles);
        resp.setMessage("Detalles filtrados por recetaId obtenidos con éxito");

        return ResponseEntity.ok(resp);
    }


    @GetMapping("/count/medicamento/{medicamentoId}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByMedicamentoId(@PathVariable Long medicamentoId) {
        Long cantidad = service.countByMedicamentoId(medicamentoId);

        ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(cantidad);
        resp.setMessage("Cantidad de detalles para el medicamento obtenida con éxito");

        return ResponseEntity.ok(resp);
    }

   
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleReceta>>> findAll() {
        List<DetalleReceta> lista = service.findAll();

        ApiResponseSuccessDto<List<DetalleReceta>> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(lista);
        resp.setMessage("Listado de DetalleRecetas obtenido con éxito");

        return ResponseEntity.ok(resp);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> findById(@PathVariable Long id) {
        DetalleReceta detalle = service.findById(id);

        ApiResponseSuccessDto<DetalleReceta> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(detalle);
        resp.setMessage("DetalleReceta encontrada con éxito");

        return ResponseEntity.ok(resp);
    }
    

       @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> save(@RequestBody DetalleRecetaRequestDto dto) {
        DetalleReceta nueva = service.create(service.fromDto(dto));

        ApiResponseSuccessDto<DetalleReceta> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(nueva);
        resp.setMessage("DetalleReceta creada con éxito");

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> update(@PathVariable Long id, @RequestBody DetalleRecetaRequestDto dto) {
        DetalleReceta entidad = service.fromDto(dto);
        DetalleReceta actualizada = service.update(id, entidad);

        ApiResponseSuccessDto<DetalleReceta> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(actualizada);
        resp.setMessage("DetalleReceta actualizada con éxito");

        return ResponseEntity.ok(resp);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteById(@PathVariable Long id) {
        service.deleteById(id);

        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(null);
        resp.setMessage("DetalleReceta eliminada con éxito");

        return ResponseEntity.ok(resp);
    }
}
