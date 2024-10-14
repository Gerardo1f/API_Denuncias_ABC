package com.denuncias.controller;

import com.denuncias.request.AdminLoginRequest;
import com.denuncias.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Endpoint para login de administradores
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody AdminLoginRequest loginRequest) {
        String token = loginService.login(loginRequest);
        return ResponseEntity.ok(token);
    }
}

