package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.FacturaRequestDto;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.service.IFacturaService;
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
            mensaje = "Lista de Facturas obtenidas obtenidas correctamente";
        }
        ApiResponseSuccessDto<List<Factura>> facturasResponse =
                new ApiResponseSuccessDto<>(true, mensaje, facturas);
        return ResponseEntity.ok(facturasResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Factura>> findById(@PathVariable Long id){
        Factura factura = facturaService.findById(id);
        ApiResponseSuccessDto<Factura> facturaResponse =
                new ApiResponseSuccessDto<>(true, "Factura con id: " + id + " obtenida correctamente", factura);
        return ResponseEntity.ok(facturaResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Factura>> createFactura(@RequestBody FacturaRequestDto requestDTO) throws Exception{
        Factura factura = facturaService.create(facturaService.fromDto(requestDTO));
        ApiResponseSuccessDto<Factura> facturaResponse =
                new ApiResponseSuccessDto<>(true, "Factura creada correctamente", factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Factura>> updateFactura(@PathVariable Long id, @RequestBody FacturaRequestDto requestDTO) throws Exception{
        Factura factura = facturaService.update(id, facturaService.fromDto(requestDTO));
        ApiResponseSuccessDto<Factura> facturaResponse =
                new ApiResponseSuccessDto<>(true, "Factura actualizada correctamente", factura);
        return ResponseEntity.ok(facturaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteFactura(@PathVariable Long id) throws Exception{
        facturaService.deleteById(id);
        ApiResponseSuccessDto<String> facturaResponse =
                new ApiResponseSuccessDto<>(true, "La factura con el ID: " + id + "fue eliminado correctamente.", "Factura ID: " + id);
        return ResponseEntity.ok(facturaResponse);
    }

}
