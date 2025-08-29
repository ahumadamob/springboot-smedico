package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.AfiliacionRequestDto;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.service.IAfiliacionService;

@RestController
@RequestMapping("/api/afiliaciones")
public class AfiliacionController {

    @Autowired
    private IAfiliacionService service;

    
    @GetMapping
    public ResponseEntity<List<Afiliacion>> getAllAfiliaciones() {
        List<Afiliacion> lista = service.findAll();

        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Afiliacion>> getAfiliacionById(@PathVariable Long id) {
        Afiliacion data = service.findById(id);

        ApiResponseSuccessDto<Afiliacion> resp =
            new ApiResponseSuccessDto<>(true, "Afiliación encontrada", data);

        return ResponseEntity.ok(resp);
    }


    @PostMapping
    public ResponseEntity<Afiliacion> createAfiliacion(@RequestBody AfiliacionRequestDto dto) throws Exception {
        Afiliacion afiliacion = service.fromDto(dto);
        return ResponseEntity.ok(service.create(afiliacion));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Afiliacion> updateAfiliacion(@PathVariable Long id, @RequestBody AfiliacionRequestDto dto) throws Exception {
        Afiliacion afiliacion = service.fromDto(dto);
        return ResponseEntity.ok(service.update(id, afiliacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAfiliacion(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}


