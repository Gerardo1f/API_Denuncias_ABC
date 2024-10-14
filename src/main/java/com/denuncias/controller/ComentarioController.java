package com.denuncias.controller;

import com.denuncias.model.Comentario;
import com.denuncias.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController
{
    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/registrar-comentario")
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario)
    {
        return comentarioService.crearComentario(comentario);
    }


}
