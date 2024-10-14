package com.denuncias.controller;

import com.denuncias.model.Denuncia;
import com.denuncias.request.EstatusRequest;
import com.denuncias.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;


    // Endpoint para obtener una denuncia por ID
    @GetMapping("/{folio}")
    public ResponseEntity<Denuncia> obtenerDenunciaPorId(@PathVariable String folio) {
        Optional<Denuncia> denuncia = denunciaService.obtenerDenunciaPorId(folio);
        return denuncia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para obtener una denuncia por folio y contraseña (seguimiento)
    @GetMapping("/seguimiento")
    public ResponseEntity<Denuncia> obtenerDenunciaPorFolioYPassword(@RequestParam String folio, @RequestParam String password) {
        Optional<Denuncia> denuncia = denunciaService.obtenerDenunciaPorFolioYPassword(folio, password);
        return denuncia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para registrar una nueva denuncia
    @PostMapping("/registrar")
    public ResponseEntity<Denuncia> registrarDenuncia(@RequestBody Denuncia denuncia) {
        Denuncia nuevaDenuncia = denunciaService.registrarDenuncia(denuncia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDenuncia);
    }

    // Endpoint para actualizar el estatus de una denuncia
    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> actualizarDenuncia(@PathVariable Long id, @RequestBody Denuncia actualizacion) {
        Denuncia denunciaActualizada = denunciaService.actualizarDenuncia(id, actualizacion);
        return ResponseEntity.ok(denunciaActualizada);
    }

    @GetMapping("/obtener-comentarios/{id}")
    public ResponseEntity<Denuncia>getDenunciaById(@PathVariable Long id)
    {
        return denunciaService.obtenerDenunciaById(id);
    }

    @PutMapping("/{id}/estatus")
    public ResponseEntity<Denuncia> cambiarEstatus(@PathVariable Long id, @RequestBody EstatusRequest request) {
        Denuncia denunciaActualizada = denunciaService.cambiarEstatus(id, request.getEstatusId());
        return ResponseEntity.ok(denunciaActualizada);
    }
}
