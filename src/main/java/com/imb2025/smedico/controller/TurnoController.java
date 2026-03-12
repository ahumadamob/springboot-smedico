package com.imb2025.smedico.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.TurnoRequestDto;
import com.imb2025.smedico.dto.response.TurnoResponseDto;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.service.ITurnoService;
import com.imb2025.smedico.mapper.TurnoMapper;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    @Autowired
    private ITurnoService service;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<TurnoResponseDto>>> findAllTurnos() {
        List<TurnoResponseDto> lista = service.findAll();
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Lista de turnos", lista));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<TurnoResponseDto>> findTurnoById(@PathVariable Long id) {
        TurnoResponseDto dto = service.findById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Turno encontrado", dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<TurnoResponseDto>> create(@Valid @RequestBody TurnoRequestDto dto) {
        TurnoResponseDto respDto = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseSuccessDto<>(true, "Turno creado", respDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<TurnoResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody TurnoRequestDto dto) {

        TurnoResponseDto respDto = service.update(id, dto);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Turno actualizado", respDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteTurno(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Turno eliminado correctamente", null));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<ApiResponseSuccessDto<List<TurnoResponseDto>>> getTurnosByFecha(
            @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<TurnoResponseDto> lista = service.findByFecha(fecha);
        String msg = lista.isEmpty() ? "No hay turnos para la fecha indicada" : "Turnos por fecha";
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, msg, lista));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countTurnosByFecha(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        long total = service.countByFecha(fecha);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Cantidad de turnos en fecha " + fecha, total));
    }
    
    @GetMapping("/recurso/severidad/baja")
    public ResponseEntity<ApiResponseSuccessDto<List<TurnoResponseDto>>> findBySeveridadLessThanEqual() {
   
        List<Turno> lista = service.findBySeveridadLessThanEqual();
        List<TurnoResponseDto> listaResponse = new ArrayList<TurnoResponseDto>();
        TurnoMapper mapper = new TurnoMapper();
        for(Turno m:lista) {
        	TurnoResponseDto dto = new TurnoResponseDto();
        	dto=mapper.toResponseDto(m);
        	listaResponse.add(dto);
        }
        ApiResponseSuccessDto<List<TurnoResponseDto>> resp;
        
        if (lista.isEmpty()) {
            resp = new ApiResponseSuccessDto<>(true,"No hay Turnos disponibles",listaResponse);
        } else {
            resp = new ApiResponseSuccessDto<>(true,"Lista de Turnos menores que 3",listaResponse);
        }
        return ResponseEntity.ok(resp); 
    }
    
    @GetMapping("/recurso/severidad/alta")
    public ResponseEntity<ApiResponseSuccessDto<List<TurnoResponseDto>>> findBySeveridadGreaterThanEqual() {
   
        List<Turno> lista = service.findBySeveridadGreaterThanEqual();
        List<TurnoResponseDto> listaResponse = new ArrayList<TurnoResponseDto>();
        TurnoMapper mapper = new TurnoMapper();
        for(Turno m:lista) {
        	TurnoResponseDto dto = new TurnoResponseDto();
        	dto=mapper.toResponseDto(m);
        	listaResponse.add(dto);
        }
        ApiResponseSuccessDto<List<TurnoResponseDto>> resp;
        
        if (lista.isEmpty()) {
            resp = new ApiResponseSuccessDto<>(true,"No hay Turnos disponibles",listaResponse);
        } else {
            resp = new ApiResponseSuccessDto<>(true,"Lista de Turnos mayores que 8",listaResponse);
        }
        return ResponseEntity.ok(resp); 
    }
    
   /* @GetMapping("/recurso/severidad/alta")
    public ResponseEntity<ApiResponseSuccessDto<List<TurnoResponseDto>>> findBySeveridadGreaterThanEqual() {
     
        List<Turno> lista = service.findBySeveridadGreaterThanEqual();
        ApiResponseSuccessDto<List<Turno>> resp  = new ApiResponseSuccessDto<>(true,
        		lista.isEmpty()?"No hay turnos disponibles " : "Lista de turnos mayores que 8",lista);
        		 return ResponseEntity.ok(resp);
    }*/
  
}
