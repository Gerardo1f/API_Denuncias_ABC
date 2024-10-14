package com.denuncias.controller;

import com.denuncias.dto.ComentarioAdminDTO;
import com.denuncias.dto.ComentarioDenuncianteDTO;
import com.denuncias.model.Comentario;
import com.denuncias.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;


    @PostMapping("/registrar-comentario")
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario) {
        return comentarioService.crearComentario(comentario);
    }

    /**
     * Endpoints para registrar comentarios en una denuncia admin y denunciante
     */

    @PostMapping("/comentarios/denunciante")
    public ResponseEntity<Comentario> agregarComentarioDenunciante(@RequestBody ComentarioDenuncianteDTO comentarioDTO) {
        Comentario nuevoComentario = comentarioService.agregarComentarioDenunciante(comentarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }

    @PostMapping("/comentarios/admin")
    public ResponseEntity<Comentario> agregarComentarioAdmin(@RequestBody ComentarioAdminDTO comentarioDTO) {
        Comentario nuevoComentario = comentarioService.agregarComentarioAdmin(comentarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }


}
