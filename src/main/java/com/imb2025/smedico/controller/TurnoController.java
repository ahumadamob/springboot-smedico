package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.TurnoRequestDto;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.service.ITurnoService;

import jakarta.validation.Valid;

@RestController
public class TurnoController {

    @Autowired
    private ITurnoService service;
    

  
    @GetMapping("/turno")
    public ResponseEntity<ApiResponseSuccessDto<List<Turno>>> findAllTurnos() {
        List<Turno> lista = service.findAll();
        ApiResponseSuccessDto<List<Turno>> resp =
                new ApiResponseSuccessDto<>(true, "Lista de turnos", lista);
        return ResponseEntity.ok(resp);
    }

    // GET - Obtener turno por ID
    @GetMapping("/turno/{idturno}")
    public ResponseEntity<ApiResponseSuccessDto<Turno>> findTurnoById(@PathVariable("idturno") Long id) {
        Turno turno = service.findById(id); 
        ApiResponseSuccessDto<Turno> resp =
                new ApiResponseSuccessDto<>(true, "Turno encontrado", turno);
        return ResponseEntity.ok(resp);
    }

    // POST - Crear turno
    @PostMapping("/turno")
    public ResponseEntity<ApiResponseSuccessDto<Turno>>create(@Valid @RequestBody TurnoRequestDto dto) throws Exception {
        Turno turno = service.create(service.fromDto(dto));
        ApiResponseSuccessDto<Turno> resp = new ApiResponseSuccessDto<>(true,"Turno creado exitosamente",turno);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
 
 // PUT - Actualizar turno
    @PutMapping("/turno/{idturno}") 
    public ResponseEntity<ApiResponseSuccessDto<Turno>> update(
            @PathVariable("idturno") Long idturno,
            @Valid @RequestBody TurnoRequestDto dto) throws Exception {

        Turno turno = service.fromDto(dto);
        Turno actualizado = service.update(idturno, turno);

        ApiResponseSuccessDto<Turno> resp = new ApiResponseSuccessDto<>(true, "Turno actualizado correctamente", actualizado);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/turno/{idturno}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteTurno(@PathVariable("idturno") Long id) {
        service.deleteById(id); 
        ApiResponseSuccessDto<Void> resp =
                new ApiResponseSuccessDto<>(true, "Turno " + id + " eliminado correctamente", null);
        return ResponseEntity.ok(resp);
    }

   
}