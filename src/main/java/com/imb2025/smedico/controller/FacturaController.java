package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.FacturaRequestDto;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.service.IFacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Factura>>> findAll(){
        List<Factura> facturas = facturaService.findAll();
        String mensaje;
        if(facturas.isEmpty()){
            mensaje = "No hay facturas disponibles";
        } else {
            mensaje = "Lista de Facturas obtenidas correctamente";
        }
        ApiResponseSuccessDto<List<Factura>> resp =
                new ApiResponseSuccessDto<>(true, mensaje, facturas);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Factura>> findById(@PathVariable Long id){
        Factura factura = facturaService.findById(id);
        ApiResponseSuccessDto<Factura> resp =
                new ApiResponseSuccessDto<>(true, "Factura con id: " + id + " obtenida correctamente", factura);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Factura>> createFactura(@Valid @RequestBody FacturaRequestDto requestDTO){
        Factura factura = facturaService.create(facturaService.fromDto(requestDTO));
        ApiResponseSuccessDto<Factura> resp =
                new ApiResponseSuccessDto<>(true, "Factura creada correctamente", factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Factura>> updateFactura(@PathVariable Long id, @Valid @RequestBody FacturaRequestDto requestDTO){
        Factura factura = facturaService.update(id, facturaService.fromDto(requestDTO));
        ApiResponseSuccessDto<Factura> resp =
                new ApiResponseSuccessDto<>(true, "Factura actualizada correctamente", factura);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteFactura(@PathVariable Long id) {
        facturaService.deleteById(id);
        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "La factura con el ID: " + id + "fue eliminado correctamente.", "Factura ID: " + id);
        return ResponseEntity.ok(resp);
    }

}
