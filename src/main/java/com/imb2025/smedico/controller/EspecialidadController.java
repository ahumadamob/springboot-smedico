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
import com.imb2025.smedico.dto.request.EspecialidadRequestDto;
import com.imb2025.smedico.dto.response.EspecialidadResponseDto;
import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.mapper.EspecialidadMapper;
import com.imb2025.smedico.service.IEspecialidadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {

        @Autowired
        private IEspecialidadService service;
               

        @GetMapping
        public ResponseEntity<ApiResponseSuccessDto<List<EspecialidadResponseDto>>> findAllEspecialidad() {        		
                List<Especialidad> lista = service.findAll();
                List<EspecialidadResponseDto> listaResponse = new ArrayList<EspecialidadResponseDto>();
                EspecialidadMapper mapper = new EspecialidadMapper();
                for(Especialidad e: lista) {
                	EspecialidadResponseDto dto = new EspecialidadResponseDto();
                	dto = mapper.toDto(e);
                	listaResponse.add(dto);
                }
                
                ApiResponseSuccessDto<List<EspecialidadResponseDto>> resp;
                if (lista.isEmpty()) {
                        resp = new ApiResponseSuccessDto<>(true, "No hay especialidades disponibles", listaResponse);
                } else {
                        resp = new ApiResponseSuccessDto<>(true, "Lista de especialidades", listaResponse);
                }
                return ResponseEntity.ok(resp);
        }

        @GetMapping("/{idespecialidad}")
        public ResponseEntity<ApiResponseSuccessDto<EspecialidadResponseDto>> findEspecialidadById(@PathVariable("idespecialidad") Long id) {
                Especialidad especialidad = service.findById(id);
                EspecialidadMapper mapper = new EspecialidadMapper();
                EspecialidadResponseDto dto = new EspecialidadResponseDto();
                dto= mapper.toDto(especialidad);
                ApiResponseSuccessDto<EspecialidadResponseDto> resp = new ApiResponseSuccessDto<>(true, "Especialidad encontrada",
                                dto);
                return ResponseEntity.ok(resp);
        }

        @GetMapping("/nombre/{nombreEspecialidad}")
        public ResponseEntity<ApiResponseSuccessDto<List<Especialidad>>> getEspecialidadByNombre(
                        @PathVariable String nombreEspecialidad) {
                List<Especialidad> lista = service.findByNombre(nombreEspecialidad);
                ApiResponseSuccessDto<List<Especialidad>> resp = new ApiResponseSuccessDto<>(true,
                                "Especialidades encontradas", lista);
                return ResponseEntity.ok(resp);
        }

        @GetMapping("/descripcion/{descripcion}")
        public ResponseEntity<ApiResponseSuccessDto<Long>> getEspecialidadCountByDescripcion(
                        @PathVariable String descripcion) {
                long cantidad = service.countByDescripcion(descripcion);
                ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(true,
                                "Cantidad de especialidades encontradas", cantidad);
                return ResponseEntity.ok(resp);
        }

        @PostMapping
        public ResponseEntity<ApiResponseSuccessDto<Especialidad>> create(@Valid @RequestBody EspecialidadRequestDto dto) {
        		
        		EspecialidadMapper mapper = new EspecialidadMapper();
                Especialidad especialidad = mapper.fromDto(dto);
                ApiResponseSuccessDto<Especialidad> resp = new ApiResponseSuccessDto<>(true,
                                "Especialidad creada correctamente", especialidad);
                return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        }

        @PutMapping("/{idespecialidad}")
        public ResponseEntity<ApiResponseSuccessDto<Especialidad>> update(@Valid @RequestBody EspecialidadRequestDto dto,
                        @PathVariable("idespecialidad") Long id) {
        		EspecialidadMapper mapper = new EspecialidadMapper();
        		Especialidad especialidadEntity = mapper.fromDto(dto);
                Especialidad actualizado = service.update(id, especialidadEntity);
                ApiResponseSuccessDto<Especialidad> resp = new ApiResponseSuccessDto<>(true,
                                "Especialidad actualizada correctamente", actualizado);
                return ResponseEntity.ok(resp);
        }

        @DeleteMapping("/{idespecialidad}")
        public ResponseEntity<ApiResponseSuccessDto<String>> deleteEspecialidad(
                        @PathVariable("idespecialidad") Long id) {
                service.deleteById(id);
                ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<>(true,
                                "Especialidad eliminada correctamente", "Id: " + id);
                return ResponseEntity.ok(resp);
        }

}
