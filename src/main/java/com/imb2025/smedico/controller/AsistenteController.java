package com.imb2025.smedico.controller;
//Controller

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.AsistenteRequestDto;
import com.imb2025.smedico.entity.Asistente;
import com.imb2025.smedico.service.IAsistenteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asistentes")
public class AsistenteController {

    @Autowired
    private IAsistenteService service;


    @GetMapping
    public ResponseEntity<List<Asistente>> findAll() {
        List<Asistente> lista = service.findAll();
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()           
                : ResponseEntity.ok(lista);                    
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Asistente>> findById(@PathVariable Long id) {
        Asistente asistente = service.findById(id);

        ApiResponseSuccessDto<Asistente> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setMessage("Asistente encontrado con éxito");
        resp.setData(asistente);

        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<Asistente> create(@Valid @RequestBody AsistenteRequestDto dto) {
        Asistente asistente = service.fromDto(dto);           // conversión DTO → entidad
        Asistente creado = service.create(asistente);         
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistente> update(@PathVariable Long id,
                                            @RequestBody AsistenteRequestDto dto) {
        Asistente asistente = service.fromDto(dto);           // conversión DTO → entidad
        Asistente actualizado = service.update(id, asistente);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        service.deleteById(id);

        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setMessage("Asistente eliminado con éxito");
        resp.setData(null);

        return ResponseEntity.ok(resp);
    }

}
