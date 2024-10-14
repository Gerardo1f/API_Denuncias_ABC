package com.denuncias.service;

import com.denuncias.model.Comentario;
import com.denuncias.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service

public class ComentarioService
{
    @Autowired
    private ComentarioRepository comentarioRepository;

    public ResponseEntity<Comentario> crearComentario(Comentario comentario)
    {
        Comentario nuevoComentario = comentarioRepository.save(comentario);
        return ResponseEntity.ok(nuevoComentario);
    }

}
