package com.denuncias.service;

import com.denuncias.dto.ComentarioAdminDTO;
import com.denuncias.dto.ComentarioDenuncianteDTO;
import com.denuncias.model.Comentario;
import com.denuncias.model.Denuncia;
import com.denuncias.repository.ComentarioRepository;
import com.denuncias.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service

public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private DenunciaRepository denunciaRepository;

    public ResponseEntity<Comentario> crearComentario(Comentario comentario) {
        Comentario nuevoComentario = comentarioRepository.save(comentario);
        return ResponseEntity.ok(nuevoComentario);
    }

    public Comentario agregarComentarioAdmin(ComentarioAdminDTO comentarioDTO) {
        Denuncia denuncia = denunciaRepository.findById(comentarioDTO.getDenunciaId())
                .orElseThrow(() -> new RuntimeException("Denuncia no encontrada"));

        Comentario comentario = new Comentario();
        comentario.setDenuncia(denuncia);
        comentario.setTexto(comentarioDTO.getTexto());
        comentario.setAutor(comentarioDTO.getAutor());
        comentario.setFecha(new Date());

        return comentarioRepository.save(comentario);
    }


    public Comentario agregarComentarioDenunciante(ComentarioDenuncianteDTO comentarioDTO) {
        Denuncia denuncia = denunciaRepository.findByFolioAndPassword(comentarioDTO.getFolio(), comentarioDTO.getPassword())
                .orElseThrow(() -> new RuntimeException("Denuncia no encontrada o contraseña incorrecta"));

        Comentario comentario = new Comentario();
        comentario.setDenuncia(denuncia);
        comentario.setTexto(comentarioDTO.getTexto());
        comentario.setAutor(comentarioDTO.getAutor());
        comentario.setFecha(new Date());

        return comentarioRepository.save(comentario);
    }


}
