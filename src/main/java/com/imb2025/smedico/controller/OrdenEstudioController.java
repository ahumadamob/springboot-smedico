package com.imb2025.smedico.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.OrdenEstudioRequestDto;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.service.IOrdenEstudioService;




@RestController
@RequestMapping("/ordenestudio")
public class OrdenEstudioController {

    @Autowired
    private IOrdenEstudioService service;
    
    @Autowired
    private MedicoRepository medicoRepository;

    // GET - Obtener todas las órdenes de estudio
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudio>>> findAllOrdenEstudio() {
        List<OrdenEstudio> orden = service.findAll();

        ApiResponseSuccessDto<List<OrdenEstudio>> resp;

        if (orden.isEmpty()) {
            resp = new ApiResponseSuccessDto<>(true, "No hay órdenes de estudio disponibles", orden);
            return ResponseEntity.noContent().build();
        } else {
            resp = new ApiResponseSuccessDto<>(true, "Lista de órdenes de estudio", orden);
            return ResponseEntity.ok(resp);
        }
    }

  
 // GET - Obtener una orden de estudio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudio>> findOrdenEstudioById(@PathVariable("id") Long id) {
        OrdenEstudio orden = service.findById(id);
        ApiResponseSuccessDto<OrdenEstudio> resp = new ApiResponseSuccessDto<>(true, "Orden de Estudio encontrada", orden);
        return ResponseEntity.ok(resp);
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


    // POST - Crear una nueva orden de estudio
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudio>> createOrdenEstudio(@RequestBody OrdenEstudioRequestDto dto) throws Exception {
        OrdenEstudio orden = service.create(service.fromDto(dto));
        ApiResponseSuccessDto<OrdenEstudio> resp =
                new ApiResponseSuccessDto<>(true, "Orden de Estudio creada correctamente", orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    // PUT - Actualizar una orden de estudio
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudio>> updateOrdenEstudio(
            @PathVariable("id") Long id, 
            @RequestBody OrdenEstudioRequestDto dto) throws Exception {
        
        OrdenEstudio orden = service.fromDto(dto);
        OrdenEstudio ordenActualizada = service.update(id, orden);

        ApiResponseSuccessDto<OrdenEstudio> resp =
                new ApiResponseSuccessDto<>(true, "Orden de Estudio actualizada correctamente", ordenActualizada);

        return ResponseEntity.ok(resp);
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