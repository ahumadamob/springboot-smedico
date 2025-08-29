package com.imb2025.smedico.controller;



import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.service.IConsultaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Consulta>>> findAll() {
        List<Consulta> lista = consultaService.findAll();
        ApiResponseSuccessDto<List<Consulta>> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(lista);
        resp.setMessage(lista.isEmpty() ? "No hay consultas" : "Listado de consultas");
        return ResponseEntity.ok(resp);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Consulta>> getById(@PathVariable Long id) {
        Consulta c = consultaService.findById(id); // lanza ResourceNotFoundException si no existe
        ApiResponseSuccessDto<Consulta> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(c);
        resp.setMessage("Consulta encontrada");
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Consulta>> create(@Valid @RequestBody ConsultaRequestDto dto) {
        Consulta creada = consultaService.create(consultaService.fromDto(dto));
        ApiResponseSuccessDto<Consulta> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(creada);
        resp.setMessage("Consulta creada");
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Consulta>> update(@PathVariable Long id,@Valid @RequestBody ConsultaRequestDto dto){
        Consulta actualizada = consultaService.update(id, consultaService.fromDto(dto));
        ApiResponseSuccessDto<Consulta> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(actualizada);
        resp.setMessage("Consulta actualizada");
        return ResponseEntity.ok(resp);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        consultaService.deleteById(id);
        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(null);
        resp.setMessage("Consulta eliminada");
        return ResponseEntity.ok(resp);
    }

}


