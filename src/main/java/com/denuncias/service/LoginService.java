package com.denuncias.service;

import com.denuncias.model.Usuario;
import com.denuncias.repository.LoginRepository;
import com.denuncias.request.AdminLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    /**
     * Metodo para autenticar al administrador
     */
    public String login(AdminLoginRequest loginRequest) {
        Usuario usuario = loginRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        if (!usuario.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return generateToken(usuario);
    }

    /**
     * Metodo ficticio para generar un token
     */
    private String generateToken(Usuario usuario) {
        return "tokenGenerado";
    }
}

