package com.imb2025.smedico.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;

@RestController
@RequestMapping("/api/version")
public class VersionController {

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<String>> getCurrentVersion() {
        ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<String>(true, "Version", "Final Marzo 2026. Buena suerte!");
        return ResponseEntity.ok(resp);
    }

}
