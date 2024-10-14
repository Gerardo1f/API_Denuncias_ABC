package com.denuncias.controller;

import com.denuncias.model.Estados;
import com.denuncias.service.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstadosController
{
    @Autowired
    private EstadosService estadosService;

    @GetMapping("/Estados/{idPais}")
    public ResponseEntity<List<String>> obtenerEstadosPorPais(@PathVariable Long idPais)
    {
        List<String> estados = estadosService.getEstadosByIdPais(idPais);
        return ResponseEntity.ok(estados);
    }

}
