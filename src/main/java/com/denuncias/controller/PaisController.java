package com.denuncias.controller;

import com.denuncias.model.Pais;
import com.denuncias.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaisController
{
    @Autowired
    private PaisService paisService;

    @GetMapping("/Pais")
    public ResponseEntity<List<Pais>> obtenerPais()
    {
        List<Pais> Pais = paisService.getAllPais();
        return ResponseEntity.ok(Pais);
    }

}
