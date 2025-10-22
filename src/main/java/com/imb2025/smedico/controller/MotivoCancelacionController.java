package com.imb2025.smedico.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.MotivoCancelacionRequestDto;
import com.imb2025.smedico.dto.response.MotivoCancelacionResponseDto;
import com.imb2025.smedico.entity.MotivoCancelacion;
import com.imb2025.smedico.mapper.MotivoCancelacionMapper;
import com.imb2025.smedico.service.IMotivoCancelacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/motivocancelacion")
public class MotivoCancelacionController {

        @Autowired
        private IMotivoCancelacionService service;

        @GetMapping
        public ResponseEntity<ApiResponseSuccessDto<List<MotivoCancelacionResponseDto>>> findAllMotivoCancelacion() {
                List<MotivoCancelacion> lista = service.findAll();
                List<MotivoCancelacionResponseDto> listaResponse = new ArrayList<MotivoCancelacionResponseDto>();
                MotivoCancelacionMapper mapper = new MotivoCancelacionMapper();
                
                for(MotivoCancelacion m: lista) {
                	MotivoCancelacionResponseDto dto = new MotivoCancelacionResponseDto();
                	dto = mapper.toDto(m);
                	listaResponse.add(dto);
                }
                
                ApiResponseSuccessDto<List<MotivoCancelacionResponseDto>> resp = new ApiResponseSuccessDto<>(true,
                                listaResponse.isEmpty() ? "No hay motivos de cancelación disponibles"
                                                : "Lista de motivos de cancelación",
                                listaResponse);
                return ResponseEntity.ok(resp);
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponseSuccessDto<MotivoCancelacionResponseDto>> findMotivoCancelacionById(
                        @PathVariable("id") Long id) {
                MotivoCancelacion motivoCancelacion = service.findById(id);
                MotivoCancelacionMapper mapper = new MotivoCancelacionMapper();
                MotivoCancelacionResponseDto dto = new MotivoCancelacionResponseDto();
                dto = mapper.toDto(motivoCancelacion);
                ApiResponseSuccessDto<MotivoCancelacionResponseDto> resp = new ApiResponseSuccessDto<>(true,
                                "Motivo de cancelación encontrado", dto);
                return ResponseEntity.ok(resp);
        }

        @PostMapping
        public ResponseEntity<ApiResponseSuccessDto<MotivoCancelacion>> create(@Valid @RequestBody MotivoCancelacionRequestDto dto) {
        		MotivoCancelacionMapper mapper = new MotivoCancelacionMapper();
                MotivoCancelacion motivoCancelacion = service.create(mapper.fromDto(dto));
                ApiResponseSuccessDto<MotivoCancelacion> resp = new ApiResponseSuccessDto<>(true,
                                "Motivo de cancelación creado correctamente", motivoCancelacion);
                return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponseSuccessDto<MotivoCancelacion>> update(@PathVariable("id") Long id,@Valid @RequestBody MotivoCancelacionRequestDto dto) {
        		MotivoCancelacionMapper mapper = new MotivoCancelacionMapper();
                MotivoCancelacion motivoEntity = mapper.fromDto(dto);
                MotivoCancelacion actualizado = service.update(id, motivoEntity);
                ApiResponseSuccessDto<MotivoCancelacion> resp = new ApiResponseSuccessDto<>(true,
                                "Motivo de cancelación actualizado correctamente", actualizado);
                return ResponseEntity.ok(resp);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponseSuccessDto<String>> deleteMotivoCancelacion(@PathVariable("id") Long id) {
                service.deleteById(id);
                ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<>(true,
                                "Motivo de cancelación eliminado correctamente", "Id: " + id);
                return ResponseEntity.ok(resp);
        }

        @GetMapping("/nombre/{nombreMotivo}")
        public ResponseEntity<ApiResponseSuccessDto<List<MotivoCancelacion>>> getMotivoCancelacionByNombre(
                        @PathVariable String nombreMotivo) {
                List<MotivoCancelacion> lista = service.findByNombre(nombreMotivo);
                ApiResponseSuccessDto<List<MotivoCancelacion>> resp = new ApiResponseSuccessDto<>(true,
                                lista.isEmpty() ? "No hay motivos de cancelación disponibles"
                                                : "Lista de motivos de cancelación",
                                lista);
                return ResponseEntity.ok(resp);
        }

        @GetMapping("/descripcion/{descripcion}")
        public ResponseEntity<ApiResponseSuccessDto<Long>> getMotivoCancelacionCountByDescripcion(
                        @PathVariable String descripcion) {
                long cantidad = service.countByDescripcion(descripcion);
                ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(true,
                                cantidad == 0 ? "No existen motivos de cancelación con esa descripcion"
                                                : "Cantidad de motivos de cancelación encontrados",
                                cantidad);
                return ResponseEntity.ok(resp);
        }
        
        @GetMapping("/stats/activos")
        public ResponseEntity<ApiResponseSuccessDto<Long>> getMotivosActivosCount() {
            long total = service.countByEstado(MotivoCancelacion.Estado.ACTIVO);
            ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(true,
                total == 0 
                    ? "No existen motivos de cancelación activos"
                    : "Cantidad de motivos de cancelación activos",
                total
            );
            return ResponseEntity.ok(resp);
        }

        @GetMapping("/stats/inactivos")
        public ResponseEntity<ApiResponseSuccessDto<Long>> getMotivosInactivosCount() {
            long total = service.countByEstado(MotivoCancelacion.Estado.INACTIVO);
            ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(true,
                total == 0 
                    ? "No existen motivos de cancelación inactivos"
                    : "Cantidad de motivos de cancelación inactivos",
                total
            );
            return ResponseEntity.ok(resp);
        }


}
