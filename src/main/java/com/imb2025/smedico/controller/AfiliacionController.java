package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.AfiliacionRequestDto;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.service.IAfiliacionService;

@RestController
@RequestMapping("/afiliacion")
public class AfiliacionController {

    @Autowired
    private IAfiliacionService servi;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Afiliacion>>>  findAllAfiliacion() {
        List<Afiliacion> lista = servi.findAll();
        ApiResponseSuccessDto<List<Afiliacion>> resp;
        if (lista.isEmpty()) {
        	resp = new ApiResponseSuccessDto<>(true,"No hay afiliaciones cargadas",lista);
        } else {
        	resp = new ApiResponseSuccessDto<>(true,"Lista de afiliaciones",lista);
        }
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Afiliacion>> findAfiliacionById(@PathVariable("id") Long idAfiliacion) {
        Afiliacion af = servi.findById(idAfiliacion);
        ApiResponseSuccessDto<Afiliacion> resp =
        		new ApiResponseSuccessDto<>(true,"Afiliacion encontrado", af);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Afiliacion>> createAfiliacion(@RequestBody AfiliacionRequestDto dto) throws Exception {
        Afiliacion afiliacion = servi.fromDto(dto);
        Afiliacion creada = servi.create(afiliacion);
        ApiResponseSuccessDto<Afiliacion> resp =
        		new ApiResponseSuccessDto<>(true,"Afiliacion creada correctamente", creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Afiliacion>> updateAfiliacion(@PathVariable Long id, @RequestBody AfiliacionRequestDto dto) throws Exception {
        Afiliacion afiliacion = servi.fromDto(dto);
        Afiliacion actualizada = servi.update(id, afiliacion);
        ApiResponseSuccessDto<Afiliacion> resp =
	            new ApiResponseSuccessDto<>(true,"Afiliacion actualizada correctamente", actualizada);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteAfiliacion(@PathVariable Long id) {
        servi.deleteById(id);
        ApiResponseSuccessDto<String> resp =
        		new ApiResponseSuccessDto<>(true,"Afiliacion eliminada correctamente","Id: "+id);
        return ResponseEntity.ok(resp);
    }

}



