package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.service.IObraSocialService;

import obrasocialrequestdto.ObraSocialRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/obrasocial")
public class ObraSocialController {

    @Autowired
    private IObraSocialService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ObraSocial> obras = service.findAll();

        if (!obras.isEmpty()) {
            return ResponseEntity.ok(obras);
        } else {
            return ResponseEntity
                    .status(200)
                    .body("Sin datos: no hay obras sociales registradas.");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            ObraSocial obraSocial = service.findById(id)
                    .orElseThrow(() -> new RuntimeException("No existe obra social para ese ID: " + id));

            return ResponseEntity.ok(obraSocial);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar la obra social: " + ex.getMessage());
        }
    }


    @PostMapping("/obrasocial")
    public ResponseEntity<?> createObraSocial(@RequestBody ObraSocialRequestDTO obraSocialRequestDto) {
        try {
            ObraSocial nuevaObraSocial = service.create(service.fromDto(obraSocialRequestDto));

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Obra social agregada exitosamente con ID: " + nuevaObraSocial.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la obra social: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarObraSocial(@PathVariable Long id,
                                                  @RequestBody ObraSocialRequestDTO dto) {
        try {
            ObraSocial obraSocial = service.findById(id)
                    .orElseThrow(() -> new RuntimeException("No se encontró una obra social con el ID: " + id));

            obraSocial.setNombre(dto.getNombre());
            obraSocial.setTelefono(dto.getTelefono());
            obraSocial.setDireccion(dto.getDireccion());
            obraSocial.setCobertura(dto.getCobertura());

            service.update(id, obraSocial);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Datos actualizados correctamente");
            response.put("obraSocial", obraSocial);

            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al actualizar la obra social: " + ex.getMessage());
        }
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

