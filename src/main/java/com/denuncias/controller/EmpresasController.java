package com.denuncias.controller;

import com.denuncias.model.Empresas;
import com.denuncias.service.EmpresasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpresasController {
    @Autowired
    private EmpresasService empresasService;

    /**
     * Endpoint para obtener empresas
     */
    @GetMapping("/Empresas")
    public ResponseEntity<List<Empresas>> obtenerEmpresas() {
        List<Empresas> empresas = empresasService.getAllEmpresas();
        return ResponseEntity.ok(empresas);
    }

}
