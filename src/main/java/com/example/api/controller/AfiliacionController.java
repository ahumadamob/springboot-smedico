package com.example.api.controller;

import com.example.api.model.UserModel;
import com.example.api.repository.IUserRepository;
import com.example.api.services.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody UserModel loginRequest) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    
    if (loginRequest.getCorreoElectronico() == null || loginRequest.getCorreoElectronico().isEmpty() ||
        loginRequest.getContrasena() == null || loginRequest.getContrasena().isEmpty()) {
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", "Los campos de correo electrónico y contraseña son obligatorios.");
        return ResponseEntity.badRequest().body(objectMapper.writeValueAsString(errorResponse));
    }

    boolean isAuthenticated = authService.validateCredentials(
            loginRequest.getCorreoElectronico(),
            loginRequest.getContrasena()
    );

    if (isAuthenticated) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("success", true);
        successResponse.put("message", "Inicio de sesión exitoso");
        return ResponseEntity.ok(objectMapper.writeValueAsString(successResponse));
    } else {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", false);
        errorResponse.put("message", "Credenciales incorrectas");
        return ResponseEntity.status(401).body(objectMapper.writeValueAsString(errorResponse));
    }
}


    @PostMapping
public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
    try {
        // Verificar si el correo electrónico ya está en uso
        if (userRepository.existsByCorreoElectronico(user.getCorreoElectronico())) {
            return ResponseEntity.status(409).body(null); 
        }
        UserModel savedUser = userRepository.save(user);
        return ResponseEntity.status(201).body(savedUser); 
    } catch (Exception e) {
        e.printStackTrace(); 
        return ResponseEntity.status(500).body(null); 
    }
}

/*    @DeleteMapping("/{correoElectronico}")
    public ResponseEntity<Void> deleteUser(@PathVariable String correoElectronico) {
        if (userRepository.existsByCorreoElectronico(correoElectronico)) {
            userRepository.deleteByCorreoElectronico(correoElectronico);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}


