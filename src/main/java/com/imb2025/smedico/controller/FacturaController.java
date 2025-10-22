package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.FacturaRequestDto;
import com.imb2025.smedico.dto.response.FacturaResponseDto;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.mapper.FacturaMapper;
import com.imb2025.smedico.service.IFacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<FacturaResponseDto>>> findAll(){
        List<Factura> facturas = facturaService.findAll();
        List<FacturaResponseDto> facturaResponseDtos = new ArrayList<>();
        FacturaMapper facturaMapper = new FacturaMapper();

        for (Factura f: facturas){
            FacturaResponseDto facturaResponseDto = facturaMapper.facturaToDto(f);
            facturaResponseDtos.add(facturaResponseDto);
        }

        String mensaje;
        if(facturaResponseDtos.isEmpty()){
            mensaje = "No hay facturas disponibles";
        } else {
            mensaje = "Lista de Facturas obtenidas correctamente";
        }
        ApiResponseSuccessDto<List<FacturaResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, mensaje, facturaResponseDtos);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<FacturaResponseDto>> findById(@PathVariable Long id){
        Factura factura = facturaService.findById(id);
        FacturaMapper facturaMapper = new FacturaMapper();
        FacturaResponseDto facturaResponseDto = facturaMapper.facturaToDto(factura);
        ApiResponseSuccessDto<FacturaResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Factura con id: " + id + " obtenida correctamente", facturaResponseDto);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<FacturaResponseDto>> createFactura(@Valid @RequestBody FacturaRequestDto requestDTO){
        FacturaMapper facturaMapper = new FacturaMapper();
        Factura factura = facturaService.create(facturaMapper.fromDto(requestDTO));
        FacturaResponseDto facturaResponseDto = facturaMapper.facturaToDto(factura);
        ApiResponseSuccessDto<FacturaResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Factura creada correctamente", facturaResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<FacturaResponseDto>> updateFactura(@PathVariable Long id, @Valid @RequestBody FacturaRequestDto requestDTO){
        FacturaMapper facturaMapper = new FacturaMapper();
        Factura factura = facturaService.update(id, facturaMapper.fromDto(requestDTO));
        FacturaResponseDto facturaResponseDto = facturaMapper.facturaToDto(factura);
        ApiResponseSuccessDto<FacturaResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Factura actualizada correctamente", facturaResponseDto);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteFactura(@PathVariable Long id) {
        facturaService.deleteById(id);
        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "La factura con el ID: " + id + "fue eliminado correctamente.", "Factura ID: " + id);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<ApiResponseSuccessDto<List<FacturaResponseDto>>> getAllFacturasByPacienteId(@PathVariable Long id){
        List<Factura> facturas = facturaService.findAllByPacienteId(id);
        List<FacturaResponseDto> facturasResponseDtos = new ArrayList<>();
        FacturaMapper facturaMapper = new FacturaMapper();

        for (Factura f: facturas){
            FacturaResponseDto facturaResponseDto = facturaMapper.facturaToDto(f);
            facturasResponseDtos.add(facturaResponseDto);
        }

        String mensaje;
        if(facturasResponseDtos.isEmpty()){
            mensaje = "No hay facturas disponibles";
        } else {
            mensaje = "Lista de Facturas obtenidas correctamente";
        }
        ApiResponseSuccessDto<List<FacturaResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, mensaje, facturasResponseDtos);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/count/{medioPago}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countFacturasByMedioPago(@PathVariable String medioPago){
        Long cantidadFacturas = facturaService.countByMedioPago(medioPago);
        ApiResponseSuccessDto<Long> resp =
                new ApiResponseSuccessDto<>(true, "Conteo de Facturas pagadas con: " + medioPago, cantidadFacturas);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/pagadas")
    public ResponseEntity<ApiResponseSuccessDto<List<FacturaResponseDto>>> getFacturasPagadas(){
        List<Factura> facturas = facturaService.findByIsPagadaTrue();
        List<FacturaResponseDto> facturasResponseDtos = new ArrayList<>();
        FacturaMapper facturaMapper = new FacturaMapper();

        for (Factura f: facturas){
            FacturaResponseDto facturaResponseDto = facturaMapper.facturaToDto(f);
            facturasResponseDtos.add(facturaResponseDto);
        }

        String mensaje;
        if(facturasResponseDtos.isEmpty()){
            mensaje = "No hay facturas disponibles";
        } else {
            mensaje = "Lista de Facturas obtenidas correctamente";
        }
        ApiResponseSuccessDto<List<FacturaResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, mensaje, facturasResponseDtos);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/no-pagadas")
    public ResponseEntity<ApiResponseSuccessDto<List<FacturaResponseDto>>> getFacturasNoPagadas(){
        List<Factura> facturas = facturaService.findByIsPagadaFalse();
        List<FacturaResponseDto> facturasResponseDtos = new ArrayList<>();
        FacturaMapper facturaMapper = new FacturaMapper();

        for (Factura f: facturas){
            FacturaResponseDto facturaResponseDto = facturaMapper.facturaToDto(f);
            facturasResponseDtos.add(facturaResponseDto);
        }

        String mensaje;
        if(facturasResponseDtos.isEmpty()){
            mensaje = "No hay facturas disponibles";
        } else {
            mensaje = "Lista de Facturas obtenidas correctamente";
        }
        ApiResponseSuccessDto<List<FacturaResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, mensaje, facturasResponseDtos);
        return ResponseEntity.ok(resp);
    }

}
