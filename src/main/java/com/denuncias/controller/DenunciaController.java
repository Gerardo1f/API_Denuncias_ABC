package com.denuncias.controller;

import com.denuncias.model.Denuncia;
import com.denuncias.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;


    /**
     * Endpoint para obtener la lista de denuncias
     */
    @GetMapping
    public ResponseEntity<List<Denuncia>> obtenerListaDenuncias() {
        List<Denuncia> denuncias = denunciaService.obtenerTodasLasDenuncias();
        return ResponseEntity.ok(denuncias);
    }

    /**
     * Endpoint para registrar una nueva denuncia
     */
    @PostMapping("/registrar")
    public ResponseEntity<Denuncia> registrarDenuncia(@RequestBody Denuncia denuncia) {
        Denuncia nuevaDenuncia = denunciaService.registrarDenuncia(denuncia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDenuncia);
    }

    /**
     * Endpoint para actualizar el estatus de una denuncia
     */
    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> actualizarDenuncia(@PathVariable Long id, @RequestBody Denuncia actualizacion) {
        Denuncia denunciaActualizada = denunciaService.actualizarDenuncia(id, actualizacion);
        return ResponseEntity.ok(denunciaActualizada);
    }

    /**
     * Obtener comentarios de una denuncia
     */
    @GetMapping("/obtener-comentarios/{id}")
    public ResponseEntity<Denuncia> getDenunciaById(@PathVariable Long id) {
        return denunciaService.obtenerDenunciaById(id);
    }

    /**
     * Endpoint para cambiar el estatus de una denuncia
     */
    @PutMapping("/{id}/estatus")
    public ResponseEntity<Denuncia> cambiarEstatus(@PathVariable Long id, @RequestBody Long estatusId) {
        Denuncia denunciaActualizada = denunciaService.cambiarEstatus(id, estatusId);
        return ResponseEntity.ok(denunciaActualizada);
    }

    /**
     * Endpoint para obtener el detalle de una denuncia por su ID
     */
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Denuncia> obtenerDenunciaPorId(@PathVariable Long id) {
        Denuncia denuncia = denunciaService.obtenerDenunciaPorId(id);
        return ResponseEntity.ok(denuncia);
    }

    /**
     * Endpoint para seguir una denuncia usando folio y contraseña denunciante
     */
    @GetMapping("/seguir")
    public ResponseEntity<Denuncia> seguirDenuncia(@RequestParam String folio, @RequestParam String password) {
        Denuncia denuncia = denunciaService.seguirDenuncia(folio, password);
        return ResponseEntity.ok(denuncia);
    }


}
