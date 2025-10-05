package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.MedicoRequestDto;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.service.IMedicoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private IMedicoService service;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Medico>>> findAllMedicos() {
        List<Medico> lista = service.findAll();
        ApiResponseSuccessDto<List<Medico>> resp;

        if (lista.isEmpty()) {
            resp = new ApiResponseSuccessDto<>(true, "No hay médicos disponibles", lista);
            return ResponseEntity.ok(resp); 
        } else {
            resp = new ApiResponseSuccessDto<>(true, "Lista de médicos", lista);
            return ResponseEntity.ok(resp);
        }
    }

    @GetMapping("/{idmedico}")
    public ResponseEntity<ApiResponseSuccessDto<Medico>> findById(@PathVariable("idmedico") Long id) {
        Medico medico = service.findById(id);
        ApiResponseSuccessDto<Medico> resp =
                new ApiResponseSuccessDto<>(true, "Médico encontrado", medico);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Medico>> create(@Valid @RequestBody MedicoRequestDto dto) {
        Medico medico = service.fromDto(dto);
        Medico creado = service.create(medico);
        ApiResponseSuccessDto<Medico> resp =
                new ApiResponseSuccessDto<>(true, "Médico creado correctamente", creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{idmedico}")
    public ResponseEntity<ApiResponseSuccessDto<Medico>> update(
            @PathVariable("idmedico") Long id,
            @Valid @RequestBody MedicoRequestDto dto) {
        Medico medico = service.fromDto(dto);
        medico.setId(id);
        Medico actualizado = service.update(id, medico);
        ApiResponseSuccessDto<Medico> resp =
                new ApiResponseSuccessDto<>(true, "Médico actualizado correctamente", actualizado);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{idmedico}")
    public ResponseEntity<ApiResponseSuccessDto<String>> delete(@PathVariable("idmedico") Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "Médico eliminado correctamente", "Id: " + id);
        return ResponseEntity.ok(resp);
    }
  
   
}
